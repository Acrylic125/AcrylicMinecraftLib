package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ButtonAction {

    /**
     *
     * @param event The event.
     * @param guiBuilder The GUI.
     */
    void run(ItemStack clickedItem, InventoryClickEvent event, AbstractGUIBuilder guiBuilder);

}
