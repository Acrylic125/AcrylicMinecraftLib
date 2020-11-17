package com.acrylic.universal.gui.exceptions;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class UnsupportedInventoryType extends RuntimeException {

    private final InventoryType inventoryType;
    private final int size;

    public UnsupportedInventoryType(Inventory inventory) {
        this.inventoryType = inventory.getType();
        this.size = inventory.getSize();
    }

    @Override
    public String toString() {
        return "UnsupportedInventoryType: " + inventoryType.name() + " of size, " + size + " is not supported in this context.";
    }
}
