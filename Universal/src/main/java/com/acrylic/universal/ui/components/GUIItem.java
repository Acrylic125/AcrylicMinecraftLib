package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUIItem {

    @NotNull
    String getID();

    @Nullable
    ItemStack getItem(InventoryDetails openDetails);

    boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Comparison uiComparableComparisonInfo);

    default boolean doesItemMatchWithThis(@NotNull ItemStack item) {
        return doesItemMatchWithThis(UIComparableItemInfo.getComparableItemInfo().createComparison(item));
    }

    @Nullable
    default ItemStack wrapGUIItem(@Nullable ItemStack item) {
        return (item == null) ? null :
                UIComparableItemInfo.getComparableItemInfo()
                .wrapItem(item)
                .idByGUIItem(this)
                .wrap();
    }

}
