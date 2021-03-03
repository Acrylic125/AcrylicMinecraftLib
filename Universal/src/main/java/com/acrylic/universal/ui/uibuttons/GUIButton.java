package com.acrylic.universal.ui.uibuttons;

import com.acrylic.universal.ui.uiitems.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface GUIButton extends GUIItem {

    void onClicked(InventoryClickEvent event);

    boolean isActive();

}
