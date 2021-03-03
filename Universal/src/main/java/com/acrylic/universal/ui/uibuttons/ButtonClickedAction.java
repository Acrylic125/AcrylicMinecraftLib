package com.acrylic.universal.ui.uibuttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ButtonClickedAction<T extends GUIButton> {

    void run(InventoryClickEvent event, T button);

}
