package com.acrylic.universal.gui.guiitems;

import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Setter
public class GUIItem extends AbstractGUIItem {

    private ItemStack item;

    public GUIItem(int slot, ItemStack item) {
        super(slot);
        this.item = item;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

}
