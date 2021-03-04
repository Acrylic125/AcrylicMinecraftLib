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

import java.util.function.Consumer;

/**
 * Global GUIs are shared among ALL PLAYERS.
 *
 * This means that when this is accessed by multiple players,
 * all parties will be able to see any modification made in this
 * GUI.
 */
public class GlobalGUI implements GUI {

    private final AbstractEventBuilder<InventoryClickEvent> generalClickEvent;
    private final AbstractEventBuilder<InventoryCloseEvent> generalCloseEvent;

    private Consumer<InventoryClickEvent> clickHandler;
    private Consumer<InventoryCloseEvent> closeHandler;
    private Inventory globalInventory;
    private GUIStaticComponent staticComponent;
    private UIFormat uiFormat;
    private boolean cancelInventoryClick = false;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(@NotNull GlobalGUI gui) {
        return new Builder(gui);
    }

    public static class Builder {
        private final GlobalGUI gui;

        private Builder() {
            this(new GlobalGUI());
        }

        private Builder(@NotNull GlobalGUI gui) {
            this.gui = gui;
        }

        public Builder inventory(@NotNull InventoryUIBuilder inventory) {
            return inventory(inventory.build());
        }

        public Builder inventory(@NotNull Inventory inventory) {
            gui.setInventory(inventory);
            return this;
        }

        public Builder clickHandler(@NotNull Consumer<InventoryClickEvent> handle) {
            gui.setOnClickHandler(handle);
            return this;
        }

        public Builder closeHandler(@NotNull Consumer<InventoryCloseEvent> handle) {
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

        public GlobalGUI build() {
            return gui;
        }
    }

    public GlobalGUI() {
        this.generalClickEvent = GUI.generateGeneralGUIClickListener(this);
        this.generalClickEvent.register();
        this.generalCloseEvent = GUI.generateGeneralGUICloseListener(this);
        this.generalCloseEvent.register();
    }

    public void setInventory(@NotNull Inventory inventory) {
        this.globalInventory = inventory;
        updateComponents();
    }

    @NotNull
    public Inventory getInventory() {
        return globalInventory;
    }

    @Override
    public void terminate() {
        this.generalCloseEvent.unregister();
        this.generalClickEvent.unregister();
    }

    public void setStaticComponent(@Nullable GUIStaticComponent staticComponent) {
        this.staticComponent = staticComponent;
        updateStaticComponent();
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
        updateUIFormat();
    }

    @Nullable
    @Override
    public UIFormat getUIFormat() {
        return uiFormat;
    }

    @Override
    public void openGUIFor(@NotNull Player player) {
        validateUse();
        player.openInventory(globalInventory);
    }

    public void updateStaticComponent() {
        if (staticComponent != null)
            staticComponent.applyComponentToInventory(globalInventory);
    }

    public void updateUIFormat() {
        if (uiFormat != null)
            uiFormat.applyComponentToInventory(globalInventory);
    }

    public void updateComponents() {
        updateStaticComponent();
        updateUIFormat();
    }

    @Override
    public void update() {
        this.generalCloseEvent.register();
        this.generalClickEvent.register();
        updateComponents();
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
        return globalInventory.equals(inventory);
    }

    private void validateUse() {
        if (globalInventory == null)
            throw new IllegalStateException("The inventory is not set!");
    }
}
