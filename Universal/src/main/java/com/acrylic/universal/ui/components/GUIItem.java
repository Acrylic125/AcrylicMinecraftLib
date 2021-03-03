package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.OpenDetails;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface GUIItem {

    @Nullable
    ItemStack getItem(OpenDetails openDetails);

}
