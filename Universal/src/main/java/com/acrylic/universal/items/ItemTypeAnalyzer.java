package com.acrylic.universal.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemTypeAnalyzer {

    default boolean isHead(@NotNull ItemStack item) {
        return isHeadMaterial(item.getType());
    }

    boolean isHeadMaterial(@NotNull Material material);

    default boolean isHelmet(@NotNull ItemStack item) {
        return isHelmetMaterial(item.getType());
    }

    boolean isHelmetMaterial(@NotNull Material material);

    default boolean isChestplate(@NotNull ItemStack item) {
        return isChestplateMaterial(item.getType());
    }

    boolean isChestplateMaterial(@NotNull Material material);

    default boolean isLeggings(@NotNull ItemStack item) {
        return isLeggingsMaterial(item.getType());
    }

    boolean isLeggingsMaterial(@NotNull Material material);

    default boolean isBoots(@NotNull ItemStack item) {
        return isBootsMaterial(item.getType());
    }

    boolean isBootsMaterial(@NotNull Material material);

    default boolean isArmor(@NotNull ItemStack item) {
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    default boolean isBow(@NotNull ItemStack item) {
        return isBootsMaterial(item.getType());
    }

    boolean isBowMaterial(@NotNull Material material);

    default boolean isSword(@NotNull ItemStack item) {
        return isSwordMaterial(item.getType());
    }

    boolean isSwordMaterial(@NotNull Material material);

    default boolean isAxe(@NotNull ItemStack item) {
        return isAxeMaterial(item.getType());
    }

    boolean isAxeMaterial(@NotNull Material material);

    default boolean isWeapon(@NotNull ItemStack item) {
        return isSword(item) || isAxe(item) || isBow(item);
    }

    default boolean isPickaxe(@NotNull ItemStack item) {
        return isPickaxeMaterial(item.getType());
    }

    boolean isPickaxeMaterial(@NotNull Material material);

    default boolean isShovel(@NotNull ItemStack item) {
        return isShovelMaterial(item.getType());
    }

    boolean isShovelMaterial(@NotNull Material material);

    default boolean isHoe(@NotNull ItemStack item) {
        return isHoeMaterial(item.getType());
    }

    boolean isHoeMaterial(@NotNull Material material);

    default boolean isTool(@NotNull ItemStack item) {
        return isPickaxe(item) || isAxe(item) || isShovel(item) || isHoe(item);
    }

    default boolean isLiquid(@NotNull ItemStack item) {
        return isLiquidMaterial(item.getType());
    }

    boolean isLiquidMaterial(@NotNull Material material);

    default boolean isAir(@Nullable Block block) {
        return block == null || block.getType().equals(Material.AIR);
    }

    default boolean isAir(@Nullable ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }

    default boolean isAir(@Nullable Material material) {
        return material == null || material.equals(Material.AIR);
    }

}
