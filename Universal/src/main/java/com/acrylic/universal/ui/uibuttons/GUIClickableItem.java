package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIClickableItem extends GUIItem {

    void onClicked(InventoryClickEvent event);

    boolean isActive();

}
