package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.OpenDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUIItem {

    @NotNull
    String getID();

    @Nullable
    ItemStack getItem(OpenDetails openDetails);

    boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Item uiComparableItemInfo);

    default boolean doesItemMatchWithThis(@NotNull ItemStack item) {
        return doesItemMatchWithThis(UIComparableItemInfo.getComparableItemInfo().createComparison(item));
    }

}
