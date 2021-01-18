package com.acrylic.universal.events;

import com.acrylic.universal.items.ItemUtils;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ArmorType {

    HELMET(5) {
        public ItemStack getItemFromEquipment(@NotNull EntityEquipment equipment) {
            return equipment.getHelmet();
        }

        @Override
        public void setItemInEquipment(@NotNull EntityEquipment equipment, @NotNull ItemStack item) {
            equipment.setHelmet(item);
        }
    }, CHESTPLATE(6) {
        @Override
        public ItemStack getItemFromEquipment(@NotNull EntityEquipment equipment) {
            return equipment.getChestplate();
        }

        @Override
        public void setItemInEquipment(@NotNull EntityEquipment equipment, @NotNull ItemStack item) {
            equipment.setChestplate(item);
        }
    }, LEGGINGS(7) {
        @Override
        public ItemStack getItemFromEquipment(@NotNull EntityEquipment equipment) {
            return equipment.getLeggings();
        }

        @Override
        public void setItemInEquipment(@NotNull EntityEquipment equipment, @NotNull ItemStack item) {
            equipment.setLeggings(item);
        }
    }, BOOTS(8) {
        @Override
        public ItemStack getItemFromEquipment(@NotNull EntityEquipment equipment) {
            return equipment.getBoots();
        }

        @Override
        public void setItemInEquipment(@NotNull EntityEquipment equipment, @NotNull ItemStack item) {
            equipment.setBoots(item);
        }
    };

    private final int slot;
    private static final ArmorType[] armorTypes = new ArmorType[] {
            HELMET, CHESTPLATE, LEGGINGS, BOOTS
    };

    ArmorType(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    @Nullable
    public abstract ItemStack getItemFromEquipment(EntityEquipment equipment);

    public abstract void setItemInEquipment(@NotNull EntityEquipment equipment, @NotNull ItemStack item);

    public static ArmorType[] getArmorTypes() {
        return armorTypes;
    }

    @Nullable
    public static ArmorType getArmorType(int slot) {
        for (ArmorType armorType : getArmorTypes()) {
            if (slot == armorType.getSlot())
                return armorType;
        }
        return null;
    }

    @Nullable
    public static ArmorType getArmorType(@Nullable ItemStack item) {
        if (ItemUtils.isAir(item))
            return null;
        if (ItemUtils.getItemTypeAnalyzer().isHelmet(item) || ItemUtils.getItemTypeAnalyzer().isHead(item))
            return HELMET;
        else if (ItemUtils.getItemTypeAnalyzer().isChestplate(item))
            return CHESTPLATE;
        else if (ItemUtils.getItemTypeAnalyzer().isLeggings(item))
            return LEGGINGS;
        else if (ItemUtils.getItemTypeAnalyzer().isBoots(item))
            return BOOTS;
        else
            return null;
    }

}
