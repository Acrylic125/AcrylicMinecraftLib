package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.UIComparableItemInfo;
import com.acrylic.universal.ui.items.GUIClickableItem;
import com.acrylic.universal.ui.items.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface GUIItemComponent extends GUIComponent {

    @NotNull
    Collection<? extends GUIItem> getGUIItems();

    default boolean findAndRunButton(InventoryClickEvent event, UIComparableItemInfo.Comparison comparison) {
        for (GUIItem guiItem : getGUIItems()) {
            if (guiItem instanceof GUIClickableItem && guiItem.doesItemMatchWithThis(comparison)) {
                GUIClickableItem item = ((GUIClickableItem) guiItem);
                item.onClicked(event, comparison);
                return true;
            }
        }
        return false;
    }

}
