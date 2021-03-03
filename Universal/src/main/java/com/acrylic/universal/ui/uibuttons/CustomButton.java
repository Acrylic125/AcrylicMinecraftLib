package com.acrylic.universal.ui.uibuttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CustomButton implements GUIButton {

    private ItemStack item;
    private ButtonClickedAction<CustomButton> clickedAction;
    private boolean active = true;

    public CustomButton(@Nullable ItemStack item, @Nullable ButtonClickedAction<CustomButton> buttonButtonClickedAction) {
        this.item = item;
        this.clickedAction = buttonButtonClickedAction;
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item;
    }

    @Nullable
    public ButtonClickedAction<CustomButton> getClickedAction() {
        return clickedAction;
    }

    public void setClickedAction(@Nullable ButtonClickedAction<CustomButton> clickedAction) {
        this.clickedAction = clickedAction;
    }

    @Override
    public void onClicked(InventoryClickEvent event) {
        if (clickedAction != null)
            this.clickedAction.run(event, this);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Nullable
    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }
}
