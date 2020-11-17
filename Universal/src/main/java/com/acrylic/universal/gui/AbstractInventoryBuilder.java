package com.acrylic.universal.gui;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public interface AbstractInventoryBuilder extends Cloneable {

    int CHEST_COLUMNS_PER_ROW = 9;

    AbstractInventoryBuilder inventoryType(InventoryType inventoryType);

    AbstractInventoryBuilder rows(int rows);

    AbstractInventoryBuilder title(String title);

    InventoryType getInventoryType();

    int getRows();

    String getTitle();

    default Inventory build() {
        String title = ChatUtils.get(getTitle());
        InventoryType type = getInventoryType();
        return (type != null) ?
                Bukkit.createInventory(null, type, title) :
                Bukkit.createInventory(null, getRows() * CHEST_COLUMNS_PER_ROW, title);
    }

}
