package com.acrylic.universal.entityanimations.equipment;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EntityEquipmentBuilderImpl implements EntityEquipmentBuilder {

    private ItemStack helmet, chestplate, leggings, boots, itemInHand, itemOffHand;
    private double helmetDropChance = 0, chestplateDropChance = 0, leggingsDropChance = 0, bootsDropChance = 0,
            itemInHandDropChance = 0, itemOffHandDropChance = 0;

    @Override
    public EntityEquipmentBuilder setHelmet(ItemStack item) {
        this.helmet = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setHelmetDropChance(double chance) {
        this.helmetDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplate(ItemStack item) {
        this.chestplate = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplateDropChance(double chance) {
        this.chestplateDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggings(ItemStack item) {
        this.leggings = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggingsDropChance(double chance) {
        this.leggingsDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBoots(ItemStack item) {
        this.boots = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBootsDropChance(double chance) {
        this.bootsDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHand(ItemStack item) {
        this.itemInHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHandDropChance(double chance) {
        this.itemInHandDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInOffHand(ItemStack item) {
        this.itemOffHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInOffhandDropChance(double chance) {
        this.itemOffHandDropChance = chance;
        return this;
    }

    @Override
    public ItemStack getHelmet() {
        return helmet;
    }

    @Override
    public double getHelmetDropChance() {
        return helmetDropChance;
    }

    @Override
    public ItemStack getChestplate() {
        return chestplate;
    }

    @Override
    public double getChestplateDropChance() {
        return chestplateDropChance;
    }

    @Override
    public ItemStack getLeggings() {
        return leggings;
    }

    @Override
    public double getLeggingsDropChance() {
        return leggingsDropChance;
    }

    @Override
    public ItemStack getBoots() {
        return boots;
    }

    @Override
    public double getBootsDropChance() {
        return bootsDropChance;
    }

    @Override
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Override
    public double getItemInHandDropChance() {
        return itemInHandDropChance;
    }

    @Override
    public ItemStack getItemInOffHand() {
        return itemOffHand;
    }

    @Override
    public double getItemInOffhandDropChance() {
        return itemOffHandDropChance;
    }

    @Override
    public void apply(@NotNull LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null : "Unexpected equipment is null? of " + entity;
        equipment.setHelmet(getHelmet());
        equipment.setChestplate(getChestplate());
        equipment.setLeggings(getLeggings());
        equipment.setBoots(getBoots());
        equipment.setItemInMainHand(getItemInHand());
        equipment.setItemInOffHand(getItemInHand());
    }

}
