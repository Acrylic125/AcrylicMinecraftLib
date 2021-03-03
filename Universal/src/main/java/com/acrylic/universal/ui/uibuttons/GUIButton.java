package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIButton extends GUIItem {

    void onClicked(InventoryClickEvent event);

    boolean isActive();

}
