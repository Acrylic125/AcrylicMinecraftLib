package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

@FunctionalInterface
public interface ButtonAction {

    /**
     *
     * @param inventory The current inventory.
     * @param inventoryView The inventoryView from {@link org.bukkit.event.inventory.InventoryClickEvent} event.
     * @param guiBuilder The GUI.
     */
    void run(Inventory inventory, InventoryView inventoryView, AbstractGUIBuilder guiBuilder);

}
