package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ButtonAction {

    /**
     *
     * @param inventory The current inventory.
     * @param inventoryView The inventoryView from {@link org.bukkit.event.inventory.InventoryClickEvent} event.
     * @param guiBuilder The GUI.
     */
    void run(ItemStack clickedItem, Inventory inventory, InventoryView inventoryView, AbstractGUIBuilder guiBuilder);

}
