package com.acrylic.universal.items;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class VanillaItemTypeAnalyzer implements ItemTypeAnalyzer {

    @Override
    public boolean isHeadMaterial(@NotNull Material material) {
        switch (material) {
            case SKELETON_SKULL:
            case WITHER_SKELETON_SKULL:
            case CREEPER_HEAD:
            case DRAGON_HEAD:
            case PLAYER_HEAD:
            case ZOMBIE_HEAD:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isHelmetMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_HELMET:
            case GOLDEN_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:
            case NETHERITE_HELMET:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isChestplateMaterial(@NotNull Material material) {
        switch (material) {
            case ELYTRA:
            case LEATHER_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isLeggingsMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case NETHERITE_LEGGINGS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isBootsMaterial(@NotNull Material material) {
        switch (material) {
            case LEATHER_BOOTS:
            case GOLDEN_BOOTS:
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
            case NETHERITE_BOOTS:
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
            case WOODEN_SWORD:
            case STONE_SWORD:
            case GOLDEN_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD:
            case NETHERITE_SWORD:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isAxeMaterial(@NotNull Material material) {
        switch (material) {
            case WOODEN_AXE:
            case STONE_AXE:
            case GOLDEN_AXE:
            case IRON_AXE:
            case DIAMOND_AXE:
            case NETHERITE_AXE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isPickaxeMaterial(@NotNull Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
            case STONE_PICKAXE:
            case GOLDEN_PICKAXE:
            case IRON_PICKAXE:
            case DIAMOND_PICKAXE:
            case NETHERITE_PICKAXE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isShovelMaterial(@NotNull Material material) {
        switch (material) {
            case WOODEN_SHOVEL:
            case STONE_SHOVEL:
            case GOLDEN_SHOVEL:
            case IRON_SHOVEL:
            case DIAMOND_SHOVEL:
            case NETHERITE_SHOVEL:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isHoeMaterial(@NotNull Material material) {
        switch (material) {
            case WOODEN_HOE:
            case STONE_HOE:
            case GOLDEN_HOE:
            case IRON_HOE:
            case DIAMOND_HOE:
            case NETHERITE_AXE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isLiquidMaterial(@NotNull Material material) {
        switch (material) {
            case WATER:
            case LAVA:
                return true;
            default:
                return false;
        }
    }

}
