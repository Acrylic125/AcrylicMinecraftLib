package com.acrylic.universal.ui.uiitems;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class BasicGUIItem implements GUIItem {

    private ItemStack item;

    public BasicGUIItem(@Nullable ItemStack item) {
        this.item = item;
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item;
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
