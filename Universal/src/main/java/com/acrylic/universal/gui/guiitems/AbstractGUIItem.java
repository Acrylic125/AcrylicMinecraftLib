package com.acrylic.universal.gui.guiitems;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Setter @Getter
public abstract class AbstractGUIItem {

    private int slot;

    public AbstractGUIItem(int slot) {
        this.slot = slot;
    }

    public void applyTo(Inventory inventory) {
        inventory.setItem(getSlot(), getItem());
    }

    public abstract ItemStack getItem();

}
