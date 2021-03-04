package com.acrylic.universal.ui;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.ui.components.GUIStaticComponent;
import com.acrylic.universal.ui.uiformats.UIFormat;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class PrivateGUI implements GUI {

    private final AbstractEventBuilder<InventoryClickEvent> generalClickEvent;
    private final AbstractEventBuilder<InventoryCloseEvent> generalCloseEvent;

    private Consumer<InventoryClickEvent> clickHandler;
    private Consumer<InventoryCloseEvent> closeHandler;
    private InventoryUIBuilder globalInventory;
    private GUIStaticComponent staticComponent;
    private UIFormat uiFormat;
    private boolean cancelInventoryClick = false;
    private final Collection<Inventory> clientInventories = new ArrayList<>();

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(@NotNull PrivateGUI gui) {
        return new Builder(gui);
    }

    public static class Builder {
        private final PrivateGUI gui;

        private Builder() {
            this(new PrivateGUI());
        }

        private Builder(@NotNull PrivateGUI gui) {
            this.gui = gui;
        }

        public Builder inventory(@NotNull InventoryUIBuilder inventory) {
            gui.setInventory(inventory);
            return this;
        }

        public Builder clickListener(@NotNull Consumer<InventoryClickEvent> handle) {
            gui.setOnClickHandler(handle);
            return this;
        }

        public Builder closeListener(@NotNull Consumer<InventoryCloseEvent> handle) {
            gui.setOnCloseHandler(handle);
            return this;
        }

        public Builder staticComponent(@Nullable GUIStaticComponent staticComponent) {
            gui.setStaticComponent(staticComponent);
            return this;
        }

        public Builder uiFormat(@Nullable UIFormat uiFormat) {
            gui.setUIFormat(uiFormat);
            return this;
        }

        public Builder cancelInventoryClick(boolean cancelInventoryClick) {
            gui.cancelInventoryClickEvent(cancelInventoryClick);
            return this;
        }

        public PrivateGUI build() {
            return gui;
        }
    }

    public PrivateGUI() {
        this.generalClickEvent = GUI.generateGeneralGUIClickListener(this);
        this.generalClickEvent.register();
        this.generalCloseEvent = generateGeneralGUICloseListener();
        this.generalCloseEvent.register();
    }

    public void setInventory(@NotNull InventoryUIBuilder inventory) {
        this.globalInventory = inventory;
    }

    @NotNull
    public InventoryUIBuilder getInventory() {
        return globalInventory;
    }

    @Override
    public void terminate() {
        this.generalClickEvent.unregister();
        this.generalCloseEvent.unregister();
    }

    public void setStaticComponent(@Nullable GUIStaticComponent staticComponent) {
        this.staticComponent = staticComponent;
    }

    @Nullable
    @Override
    public GUIStaticComponent getStaticComponent() {
        return staticComponent;
    }

    @Nullable
    @Override
    public Consumer<InventoryClickEvent> getOnClickHandler() {
        return clickHandler;
    }

    @Override
    public void setOnClickHandler(@Nullable Consumer<InventoryClickEvent> handle) {
        this.clickHandler = handle;
    }

    @Nullable
    @Override
    public Consumer<InventoryCloseEvent> getOnCloseHandler() {
        return closeHandler;
    }

    @Override
    public void setOnCloseHandler(@Nullable Consumer<InventoryCloseEvent> handle) {
        this.closeHandler = handle;
    }

    public void setUIFormat(@Nullable UIFormat uiFormat) {
        this.uiFormat = uiFormat;
    }

    @Nullable
    @Override
    public UIFormat getUIFormat() {
        return uiFormat;
    }

    @Override
    public void openGUIFor(@NotNull Player player) {
        validateUse();
        Inventory inventory = globalInventory.build();
        if (staticComponent != null)
            staticComponent.applyComponentToInventory(inventory, player);
        if (uiFormat != null)
           uiFormat.applyComponentToInventory(inventory, player);
        clientInventories.add(inventory);
        player.openInventory(inventory);
    }

    public void openSameGUIFor(@NotNull Player mainViewer, @NotNull Player... players) {
        validateUse();
        Inventory inventory = globalInventory.build();
        if (staticComponent != null)
            staticComponent.applyComponentToInventory(inventory, mainViewer);
        if (uiFormat != null)
            uiFormat.applyComponentToInventory(inventory, mainViewer);
        clientInventories.add(inventory);
        for (Player player : players)
            player.openInventory(inventory);
    }

    @Override
    public void update() {
        this.generalClickEvent.register();
        this.generalCloseEvent.register();
    }

    public void cancelInventoryClickEvent(boolean b) {
        this.cancelInventoryClick = b;
    }

    @Override
    public boolean shouldCancelInventoryClickEvent() {
        return cancelInventoryClick;
    }

    @Override
    public boolean containsInventory(@NotNull Inventory inventory) {
        validateUse();
        return clientInventories.contains(inventory);
    }

    private void validateUse() {
        if (globalInventory == null)
            throw new IllegalStateException("The inventory is not set!");
    }

    private AbstractEventBuilder<InventoryCloseEvent> generateGeneralGUICloseListener() {
        return GUI.bindListenerToGUI(this, GUI.generateListener(this, InventoryCloseEvent.class, "General GUI Close Listener")
                .handle(event -> {
                    runCloseHandler(event);
                    Inventory inventory = event.getInventory();
                    if (shouldCancelInventoryClickEvent() && inventory.getViewers().size() <= 1)
                        clientInventories.remove(inventory);
                })
        );
    }

}
