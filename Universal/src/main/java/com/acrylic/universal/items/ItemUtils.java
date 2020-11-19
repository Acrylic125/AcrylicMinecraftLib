package com.acrylic.universal.items;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class ItemUtils {

    public boolean isAir(@Nullable ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }

    public boolean isNotAir(@Nullable ItemStack item) {
        return !isAir(item);
    }

}
