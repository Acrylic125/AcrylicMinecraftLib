package com.acrylic.universal.ui.items;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface ItemClickAction<T extends GUIClickableItem> {

    void run(InventoryClickEvent event, T button);

}
