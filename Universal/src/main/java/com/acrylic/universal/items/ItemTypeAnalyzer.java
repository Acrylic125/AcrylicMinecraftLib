package com.acrylic.universal.items;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemTypeAnalyzer {

    boolean isHead(@NotNull ItemStack item);

    boolean isHelmet(@NotNull ItemStack item);

    boolean isChestplate(@NotNull ItemStack item);

    boolean isLeggings(@NotNull ItemStack item);

    boolean isBoots(@NotNull ItemStack item);

    default boolean isArmor(@NotNull ItemStack item) {
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    boolean isBow(@NotNull ItemStack item);

    boolean isSword(@NotNull ItemStack item);

    boolean isAxe(@NotNull ItemStack item);

    default boolean isWeapon(@NotNull ItemStack item) {
        return isSword(item) || isAxe(item) || isBow(item);
    }

    boolean isPickaxe(@NotNull ItemStack item);

    boolean isShovel(@NotNull ItemStack item);

    boolean isHoe(@NotNull ItemStack item);

    default boolean isTool(@NotNull ItemStack item) {
        return isPickaxe(item) || isAxe(item) || isShovel(item) || isHoe(item);
    }

}
