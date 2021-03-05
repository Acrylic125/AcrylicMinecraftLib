package com.acrylic.universal.ui.items;

import com.acrylic.universal.ui.UIComparableItemInfo;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public interface GUIClickableItem extends GUIItem {

    void onClicked(InventoryClickEvent event, @NotNull UIComparableItemInfo.Comparison itemComparison);

    boolean isActive();

}
