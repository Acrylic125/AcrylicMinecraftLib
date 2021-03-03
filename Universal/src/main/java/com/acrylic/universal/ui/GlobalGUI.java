package com.acrylic.universal.ui;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.ui.components.GUIItem;
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
 *
 * @param <T> The item type.
 */
public class GlobalGUI<T extends GUIItem>
        implements GUI<T> {

    private Inventory globalInventory;
    private AbstractEventBuilder<InventoryClickEvent> clickEvent;
    private AbstractEventBuilder<InventoryCloseEvent> closeEvent;
    private GUIStaticComponent<T> staticComponent;
    private UIFormat<T> uiFormat;

    public void setInventory(@NotNull Inventory inventory) {
        this.globalInventory = inventory;
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
    }

    public void setStaticComponent(@Nullable GUIStaticComponent<T> staticComponent) {
        this.staticComponent = staticComponent;
    }

    @Nullable
    @Override
    public GUIStaticComponent<T> getStaticComponent() {
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

    public void setUIFormat(@Nullable UIFormat<T> uiFormat) {
        this.uiFormat = uiFormat;
        if (uiFormat != null)
            uiFormat.applyComponentToInventory(globalInventory);
    }

    @Nullable
    @Override
    public UIFormat<T> getUIFormat() {
        return uiFormat;
    }

    @Override
    public void openGUIFor(@NotNull Player player) {
        validateUse();
        player.openInventory(globalInventory);
    }

    public void updateComponents() {
        if (staticComponent != null)
           staticComponent.applyComponentToInventory(globalInventory, null);
        if (uiFormat != null)
            uiFormat.applyComponentToInventory(globalInventory);
    }

    @Override
    public void update() {
        if (clickEvent != null)
            clickEvent.register();
        if (closeEvent != null)
            closeEvent.register();
        updateComponents();
    }

    public void cancelInventoryClickEvent(boolean b) {

    }

    @Override
    public boolean shouldCancelInventoryClickEvent() {
        return false;
    }

    @Override
    public boolean containsInventory(@NotNull Inventory inventory) {
        return globalInventory == inventory;
    }

    private void validateUse() {
        if (globalInventory == null)
            throw new IllegalStateException("The inventory is not set!");
    }
}
