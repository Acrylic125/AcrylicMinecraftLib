package com.acrylic.version_1_8.items;

import com.acrylic.universal.items.ItemTypeAnalyzer;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class VanillaItemTypeAnalyzer implements ItemTypeAnalyzer {

    @Override
    public boolean isHeadMaterial(@NotNull Material material) {
        switch (material) {
            case SKULL:
            case SKULL_ITEM:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isHelmetMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_HELMET:
            case GOLD_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isChestplateMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_CHESTPLATE:
            case GOLD_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isLeggingsMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_LEGGINGS:
            case GOLD_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isBootsMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_BOOTS:
            case GOLD_BOOTS:
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isBowMaterial(@NotNull Material material) {
        return material.equals(Material.BOW);
    }

    @Override
    public boolean isSwordMaterial(@NotNull Material material) {
        switch (material) {
            case WOOD_SWORD:
            case STONE_SWORD:
            case GOLD_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isAxeMaterial(@NotNull Material material) {
        switch (material) {
            case WOOD_AXE:
            case STONE_AXE:
            case GOLD_AXE:
            case IRON_AXE:
            case DIAMOND_AXE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isPickaxeMaterial(@NotNull Material material) {
        switch (material) {
            case WOOD_PICKAXE:
            case STONE_PICKAXE:
            case GOLD_PICKAXE:
            case IRON_PICKAXE:
            case DIAMOND_PICKAXE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isShovelMaterial(@NotNull Material material) {
        switch (material) {
            case WOOD_SPADE:
            case STONE_SPADE:
            case GOLD_SPADE:
            case IRON_SPADE:
            case DIAMOND_SPADE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isHoeMaterial(@NotNull Material material) {
        switch (material) {
            case WOOD_HOE:
            case STONE_HOE:
            case GOLD_HOE:
            case IRON_HOE:
            case DIAMOND_HOE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isLiquidMaterial(@NotNull Material material) {
        switch (material) {
            case STATIONARY_WATER:
            case WATER:
            case STATIONARY_LAVA:
            case LAVA:
                return true;
            default:
                return false;
        }
    }
}
