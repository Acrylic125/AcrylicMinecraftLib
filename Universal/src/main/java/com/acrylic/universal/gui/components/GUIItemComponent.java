package com.acrylic.universal.gui.components;

import com.acrylic.universal.gui.GUI;
import com.acrylic.universal.gui.UIComparableItemInfo;
import com.acrylic.universal.gui.items.GUIClickableItem;
import com.acrylic.universal.gui.items.GUIItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface GUIItemComponent extends GUIComponent {

    @NotNull
    Collection<? extends GUIItem> getGUIItems();

    default boolean findAndRunButton(GUI gui, InventoryClickEvent event, UIComparableItemInfo.Comparison comparison) {
        for (GUIItem guiItem : getGUIItems()) {
            if (guiItem instanceof GUIClickableItem && guiItem.doesItemMatchWithThis(comparison)) {
                GUIClickableItem item = ((GUIClickableItem) guiItem);
                item.onClicked(gui, event, comparison);
                return true;
            }
        }
        return false;
    }

}
