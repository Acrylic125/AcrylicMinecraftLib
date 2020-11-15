package com.acrylic.universal.gui.templates;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Setter @Getter
public class GUIItem {

    private int slot;
    private ItemStack item;

    public GUIItem(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
    }

}
