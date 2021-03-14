package com.acrylic.version_1_8.equipment;

import com.acrylic.universal.entity.equipment.EntityEquipmentBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EntityEquipmentBuilderImpl implements EntityEquipmentBuilder {

    private ItemStack helmet, chestplate, leggings, boots, itemInHand;

    @Override
    public EntityEquipmentBuilder setHelmet(ItemStack item) {
        this.helmet = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplate(ItemStack item) {
        this.chestplate = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggings(ItemStack item) {
        this.leggings = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBoots(ItemStack item) {
        this.boots = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHand(ItemStack item) {
        this.itemInHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInOffHand(ItemStack item) {
        throw new IllegalStateException("1.8 does not support items in offhand.");
    }

    @Override
    public ItemStack getHelmet() {
        return helmet;
    }

    @Override
    public ItemStack getChestplate() {
        return chestplate;
    }

    @Override
    public ItemStack getLeggings() {
        return leggings;
    }

    @Override
    public ItemStack getBoots() {
        return boots;
    }

    @Override
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Override
    public ItemStack getItemInOffHand() {
        throw new IllegalStateException("1.8 does not support items in offhand.");
    }

    @Override
    public void apply(@NotNull LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null : "Unexpected equipment is null? of " + entity;
        equipment.setHelmet(getHelmet());
        equipment.setChestplate(getChestplate());
        equipment.setLeggings(getLeggings());
        equipment.setBoots(getBoots());
        equipment.setItemInHand(getItemInHand());
    }

}
