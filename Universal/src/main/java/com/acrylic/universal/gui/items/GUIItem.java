package com.acrylic.universal.gui.items;

import com.acrylic.universal.gui.InventoryDetails;
import com.acrylic.universal.gui.UIComparableItemInfo;
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
        UIComparableItemInfo.Comparison comparison = UIComparableItemInfo.getComparableItemInfo().createComparison(item);
        return comparison != null && doesItemMatchWithThis(comparison);
    }

    @Nullable
    default UIComparableItemInfo.Wrapper wrapperGUIItem(@Nullable ItemStack item) {
        return (item == null) ? null :
                UIComparableItemInfo.getComparableItemInfo()
                        .wrapItem(item)
                        .idByGUIItem(this);
    }

    @Nullable
    default ItemStack wrapGUIItem(@Nullable ItemStack item) {
        UIComparableItemInfo.Wrapper wrapper = wrapperGUIItem(item);
        return (wrapper == null) ? null : wrapper.wrap();
    }

}
