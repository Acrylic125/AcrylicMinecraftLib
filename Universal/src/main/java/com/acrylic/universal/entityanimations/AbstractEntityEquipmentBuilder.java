package com.acrylic.universal.entityanimations;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface AbstractEntityEquipmentBuilder {

    AbstractEntityEquipmentBuilder setHelmet(ItemStack item);

    AbstractEntityEquipmentBuilder setChestplate(ItemStack item);

    AbstractEntityEquipmentBuilder setLeggings(ItemStack item);

    AbstractEntityEquipmentBuilder setBoots(ItemStack item);

    AbstractEntityEquipmentBuilder setItemInHand(ItemStack item);

    ItemStack getHelmet();

    ItemStack getChestplate();

    ItemStack getLeggings();

    ItemStack getBoots();

    ItemStack getItemInHand();

    void apply(@NotNull LivingEntity entity);

}
