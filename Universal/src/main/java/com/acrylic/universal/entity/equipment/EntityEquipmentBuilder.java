package com.acrylic.universal.entity.equipment;

import com.acrylic.universal.Universal;
import com.acrylic.universal.items.AbstractItemBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityEquipmentBuilder {

    default EntityEquipmentBuilder setHelmet(@Nullable AbstractItemBuilder itemBuilder) {
        return setHelmet((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setHelmet(@Nullable ItemStack item);

    default EntityEquipmentBuilder setChestplate(@Nullable AbstractItemBuilder itemBuilder) {
        return setChestplate((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setChestplate(@Nullable ItemStack item);

    default EntityEquipmentBuilder setLeggings(@Nullable AbstractItemBuilder itemBuilder) {
        return setLeggings((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setLeggings(@Nullable ItemStack item);

    default EntityEquipmentBuilder setBoots(@Nullable AbstractItemBuilder itemBuilder) {
        return setBoots((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setBoots(@Nullable ItemStack item);

    default EntityEquipmentBuilder setItemInHand(@Nullable AbstractItemBuilder itemBuilder) {
        return setItemInHand((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setItemInHand(@Nullable ItemStack item);

    default EntityEquipmentBuilder setItemInOffHand(@Nullable AbstractItemBuilder itemBuilder) {
        return setItemInOffHand((itemBuilder == null) ? null : itemBuilder.build());
    }

    EntityEquipmentBuilder setItemInOffHand(@Nullable ItemStack item);

    ItemStack getHelmet();

    ItemStack getChestplate();

    ItemStack getLeggings();

    ItemStack getBoots();

    ItemStack getItemInHand();

    ItemStack getItemInOffHand();

    void apply(@NotNull LivingEntity entity);

    static void cloneEquipment(@NotNull EntityEquipment cloneFrom, @NotNull EntityEquipment cloneTo) {
        cloneTo.setHelmet(cloneFrom.getHelmet());
        cloneTo.setHelmetDropChance(cloneFrom.getHelmetDropChance());
        cloneTo.setChestplate(cloneFrom.getChestplate());
        cloneTo.setChestplateDropChance(cloneFrom.getChestplateDropChance());
        cloneTo.setLeggings(cloneFrom.getLeggings());
        cloneTo.setLeggingsDropChance(cloneFrom.getLeggingsDropChance());
        cloneTo.setBoots(cloneFrom.getBoots());
        cloneTo.setBootsDropChance(cloneFrom.getBootsDropChance());
        if (Universal.getAcrylicPlugin().getVersionStore().isLegacyVersion()) {
            cloneTo.setItemInHand(cloneFrom.getItemInHand());
            cloneTo.setItemInHandDropChance(cloneFrom.getItemInHandDropChance());
        } else {
            cloneTo.setItemInMainHand(cloneFrom.getItemInMainHand());
            cloneTo.setItemInMainHandDropChance(cloneFrom.getItemInMainHandDropChance());
            cloneTo.setItemInOffHand(cloneFrom.getItemInOffHand());
            cloneTo.setItemInOffHandDropChance(cloneFrom.getItemInOffHandDropChance());
        }
    }

}
