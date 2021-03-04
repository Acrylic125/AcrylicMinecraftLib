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
public class PrivateGUI implements GUI {

    private Inventory globalInventory;
    private AbstractEventBuilder<InventoryClickEvent> clickEvent;
    private AbstractEventBuilder<InventoryCloseEvent> closeEvent;
    private final AbstractEventBuilder<InventoryClickEvent> generalClickEvent;
    private GUIStaticComponent staticComponent;
    private UIFormat uiFormat;
    private boolean cancelInventoryClick = false;

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
            return inventory(inventory.build());
        }

        public Builder inventory(@NotNull Inventory inventory) {
            gui.setInventory(inventory);
            return this;
        }

        public Builder clickListener(@NotNull Consumer<InventoryClickEvent> handle) {
            gui.setClickListener(handle);
            return this;
        }

        public Builder clickListener(@Nullable AbstractEventBuilder<InventoryClickEvent> clickEvent) {
            gui.setClickListener(clickEvent);
            return this;
        }

        public Builder closeListener(@NotNull Consumer<InventoryCloseEvent> handle) {
            gui.setCloseListener(handle);
            return this;
        }

        public Builder closeListener(@Nullable AbstractEventBuilder<InventoryCloseEvent> closeEvent) {
            gui.setCloseListener(closeEvent);
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
        if (clickEvent != null)
            clickEvent.unregister();
        if (closeEvent != null)
            closeEvent.unregister();
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

    public void setClickListener(@NotNull Consumer<InventoryClickEvent> handle) {
        setClickListener(GUI
                .generateListener(this, InventoryClickEvent.class, "GUI Click Listener (Provided)")
                .handle(handle)
        );
    }

    public void setClickListener(@Nullable AbstractEventBuilder<InventoryClickEvent> listener) {
        if (listener == null) {
            if (this.clickEvent != null)
                this.clickEvent.unregister();
        } else {
            GUI.bindListenerToGUI(this, listener);
            listener.register();
        }
        this.clickEvent = listener;
    }

    @Nullable
    @Override
    public AbstractEventBuilder<InventoryClickEvent> getClickListener() {
        return clickEvent;
    }

    public void setCloseListener(@NotNull Consumer<InventoryCloseEvent> handle) {
        setCloseListener(GUI
                .generateListener(this, InventoryCloseEvent.class, "GUI Close Listener (Provided)")
                .handle(handle)
        );
    }

    public void setCloseListener(@Nullable AbstractEventBuilder<InventoryCloseEvent> listener) {
        if (listener == null) {
            if (this.closeEvent != null)
                this.closeEvent.unregister();
        } else {
            GUI.bindListenerToGUI(this, listener);
            listener.register();
        }
        this.closeEvent = listener;
    }

    @Nullable
    @Override
    public AbstractEventBuilder<InventoryCloseEvent> getCloseListener() {
        return closeEvent;
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
        if (clickEvent != null)
            clickEvent.register();
        if (closeEvent != null)
            closeEvent.register();
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
