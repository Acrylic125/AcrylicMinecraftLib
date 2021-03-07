package com.acrylic.universal.gui.items;

import com.acrylic.universal.gui.GUI;
import com.acrylic.universal.gui.UIComparableItemInfo;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public interface GUIClickableItem extends GUIItem {

    void onClicked(@NotNull GUI gui, @NotNull InventoryClickEvent event, @NotNull UIComparableItemInfo.Comparison itemComparison);

    boolean isActive();

}
