package com.acrylic.universal.entityanimations.equipment;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface EntityEquipmentBuilder {

    EntityEquipmentBuilder setHelmet(ItemStack item);

    EntityEquipmentBuilder setHelmetDropChance(double chance);

    EntityEquipmentBuilder setChestplate(ItemStack item);

    EntityEquipmentBuilder setChestplateDropChance(double chance);

    EntityEquipmentBuilder setLeggings(ItemStack item);

    EntityEquipmentBuilder setLeggingsDropChance(double chance);

    EntityEquipmentBuilder setBoots(ItemStack item);

    EntityEquipmentBuilder setBootsDropChance(double chance);

    EntityEquipmentBuilder setItemInHand(ItemStack item);

    EntityEquipmentBuilder setItemInHandDropChance(double chance);

    EntityEquipmentBuilder setItemInOffHand(ItemStack item);

    EntityEquipmentBuilder setItemInOffhandDropChance(double chance);

    ItemStack getHelmet();

    double getHelmetDropChance();

    ItemStack getChestplate();

    double getChestplateDropChance();

    ItemStack getLeggings();

    double getLeggingsDropChance();

    ItemStack getBoots();

    double getBootsDropChance();

    ItemStack getItemInHand();

    double getItemInHandDropChance();

    ItemStack getItemInOffHand();

    double getItemInOffhandDropChance();

    void apply(@NotNull LivingEntity entity);

}
