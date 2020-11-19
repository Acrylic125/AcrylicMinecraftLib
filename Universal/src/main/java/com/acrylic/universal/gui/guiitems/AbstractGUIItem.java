package com.acrylic.universal.gui.guiitems;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface AbstractGUIItem {

    int getSlot();

    ItemStack getItem();

    default void applyTo(Inventory inventory) {
        inventory.setItem(getSlot(), getItem());
    }

}
