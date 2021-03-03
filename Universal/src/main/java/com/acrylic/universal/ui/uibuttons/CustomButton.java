package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.OpenDetails;
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

    public CustomButton setItem(@Nullable ItemStack item) {
        this.item = item;
        return this;
    }

    @Nullable
    public ButtonClickedAction<CustomButton> getClickedAction() {
        return clickedAction;
    }

    public CustomButton setClickedAction(@Nullable ButtonClickedAction<CustomButton> clickedAction) {
        this.clickedAction = clickedAction;
        return this;
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

    public CustomButton setActive(boolean active) {
        this.active = active;
        return this;
    }

    @Nullable
    @Override
    public ItemStack getItem(OpenDetails openDetails) {
        return item;
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }
}
