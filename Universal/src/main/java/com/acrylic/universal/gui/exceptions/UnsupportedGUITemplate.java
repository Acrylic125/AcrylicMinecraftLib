package com.acrylic.universal.gui.exceptions;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class UnsupportedGUITemplate extends RuntimeException {

    @Override
    public String toString() {
        return "UnsupportedGUITemplate: This template is not supported in this context.";
    }
}
