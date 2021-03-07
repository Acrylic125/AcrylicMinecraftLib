package com.acrylic.version_1_8.equipment;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entity.equipment.EntityEquipmentBuilder;
import org.bukkit.Bukkit;
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
    public EntityEquipmentBuilder setHelmetDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public EntityEquipmentBuilder setChestplate(ItemStack item) {
        this.chestplate = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplateDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public EntityEquipmentBuilder setLeggings(ItemStack item) {
        this.leggings = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggingsDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public EntityEquipmentBuilder setBoots(ItemStack item) {
        this.boots = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBootsDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public EntityEquipmentBuilder setItemInHand(ItemStack item) {
        this.itemInHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHandDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public EntityEquipmentBuilder setItemInOffHand(ItemStack item) {
        throw new IllegalStateException("1.8 does not support items in offhand.");
    }

    @Override
    public EntityEquipmentBuilder setItemInOffhandDropChance(float chance) {
        throw new IllegalStateException("1.8 does not support items in offhand.");
    }

    @Override
    public ItemStack getHelmet() {
        return helmet;
    }

    @Override
    public float getHelmetDropChance() {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public ItemStack getChestplate() {
        return chestplate;
    }

    @Override
    public float getChestplateDropChance() {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public ItemStack getLeggings() {
        return leggings;
    }

    @Override
    public float getLeggingsDropChance() {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public ItemStack getBoots() {
        return boots;
    }

    @Override
    public float getBootsDropChance() {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Override
    public float getItemInHandDropChance() {
        throw new IllegalStateException("1.8 does not support drop chance.");
    }

    @Override
    public ItemStack getItemInOffHand() {
        throw new IllegalStateException("1.8 does not support items in offhand.");
    }

    @Override
    public float getItemInOffhandDropChance() {
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
