package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.OpenDetails;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CustomClickableItem implements GUIClickableItem {

    private ItemStack item;
    private ItemClickAction<CustomClickableItem> clickedAction;
    private boolean active = true;

    public CustomClickableItem(@Nullable ItemStack item, @Nullable ItemClickAction<CustomClickableItem> buttonItemClickAction) {
        this.item = item;
        this.clickedAction = buttonItemClickAction;
    }

    public CustomClickableItem setItem(@Nullable ItemStack item) {
        this.item = item;
        return this;
    }

    @Nullable
    public ItemClickAction<CustomClickableItem> getClickedAction() {
        return clickedAction;
    }

    public CustomClickableItem setClickedAction(@Nullable ItemClickAction<CustomClickableItem> clickedAction) {
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

    public CustomClickableItem setActive(boolean active) {
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
