package com.acrylic.universal.gui.guiitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPlayerGUIItem extends GUIItem {

    public AbstractPlayerGUIItem(int slot, ItemStack item) {
        super(slot, item);
    }

    public abstract void applyTo(@NotNull final Inventory inventory, @NotNull final Player viewer);

}
