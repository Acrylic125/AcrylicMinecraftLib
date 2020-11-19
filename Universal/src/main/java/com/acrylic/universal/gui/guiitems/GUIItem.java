package com.acrylic.universal.gui.guiitems;

import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Setter
public class GUIItem implements AbstractGUIItem {

    private ItemStack item;
    private final int slot;

    public GUIItem(int slot, ItemStack item) {
        this.item = item;
        this.slot = slot;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public int getSlot() {
        return slot;
    }
}
