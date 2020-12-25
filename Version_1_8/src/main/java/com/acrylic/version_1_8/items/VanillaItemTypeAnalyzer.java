package com.acrylic.version_1_8.items;

import com.acrylic.universal.items.ItemTypeAnalyzer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class VanillaItemTypeAnalyzer implements ItemTypeAnalyzer {

    @Override
    public boolean isHead(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.SKULL) || material.equals(Material.SKULL_ITEM);
    }

    @Override
    public boolean isHelmet(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.LEATHER_HELMET) || material.equals(Material.GOLD_HELMET) || material.equals(Material.CHAINMAIL_HELMET) || material.equals(Material.IRON_HELMET) || material.equals(Material.DIAMOND_HELMET);
    }

    @Override
    public boolean isChestplate(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.LEATHER_CHESTPLATE) || material.equals(Material.GOLD_CHESTPLATE) || material.equals(Material.CHAINMAIL_CHESTPLATE) || material.equals(Material.IRON_CHESTPLATE) || material.equals(Material.DIAMOND_CHESTPLATE);
    }

    @Override
    public boolean isLeggings(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.LEATHER_LEGGINGS) || material.equals(Material.GOLD_LEGGINGS) || material.equals(Material.CHAINMAIL_LEGGINGS) || material.equals(Material.IRON_LEGGINGS) || material.equals(Material.DIAMOND_LEGGINGS);
    }

    @Override
    public boolean isBoots(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.LEATHER_BOOTS) || material.equals(Material.GOLD_BOOTS) || material.equals(Material.CHAINMAIL_BOOTS) || material.equals(Material.IRON_BOOTS) || material.equals(Material.DIAMOND_BOOTS);
    }

    @Override
    public boolean isBow(@NotNull ItemStack item) {
        return item.getType().equals(Material.BOW);
    }

    @Override
    public boolean isSword(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.WOOD_SWORD) || material.equals(Material.GOLD_SWORD) || material.equals(Material.STONE_SWORD) || material.equals(Material.IRON_SWORD) || material.equals(Material.DIAMOND_SWORD);
    }

    @Override
    public boolean isAxe(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.WOOD_AXE) || material.equals(Material.GOLD_AXE) || material.equals(Material.STONE_AXE) || material.equals(Material.IRON_AXE) || material.equals(Material.DIAMOND_AXE);
    }

    @Override
    public boolean isPickaxe(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.WOOD_PICKAXE) || material.equals(Material.GOLD_PICKAXE) || material.equals(Material.STONE_PICKAXE) || material.equals(Material.IRON_PICKAXE) || material.equals(Material.DIAMOND_PICKAXE);
    }

    @Override
    public boolean isShovel(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.WOOD_SPADE) || material.equals(Material.GOLD_SPADE) || material.equals(Material.STONE_SPADE) || material.equals(Material.IRON_SPADE) || material.equals(Material.DIAMOND_SPADE);
    }

    @Override
    public boolean isHoe(@NotNull ItemStack item) {
        Material material = item.getType();
        return material.equals(Material.WOOD_HOE) || material.equals(Material.GOLD_HOE) || material.equals(Material.STONE_HOE) || material.equals(Material.IRON_HOE) || material.equals(Material.DIAMOND_HOE);
    }
}
