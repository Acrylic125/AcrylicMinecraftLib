package com.acrylic.universal.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PrivateGUIBuilder extends GUIBuilder {



    @Override
    public AbstractGUIBuilder setItem(int slot, ItemStack item) {
        return null;
    }

    @Override
    public AbstractGUIBuilder open(Player... viewers) {
        return null;
    }
}
