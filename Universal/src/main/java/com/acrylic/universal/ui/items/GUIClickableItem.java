package com.acrylic.universal.ui.items;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIClickableItem extends GUIItem {

    void onClicked(InventoryClickEvent event);

    boolean isActive();

}
