package com.acrylic.universal.entity.equipment;

import com.acrylic.universal.Universal;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EntityEquipmentBuilderImpl implements EntityEquipmentBuilder {

    private ItemStack helmet, chestplate, leggings, boots, itemInHand, itemOffHand;
    private float helmetDropChance = 0, chestplateDropChance = 0, leggingsDropChance = 0, bootsDropChance = 0,
            itemInHandDropChance = 0, itemOffHandDropChance = 0;

    @Override
    public EntityEquipmentBuilder setHelmet(ItemStack item) {
        this.helmet = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setHelmetDropChance(float chance) {
        this.helmetDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplate(ItemStack item) {
        this.chestplate = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setChestplateDropChance(float chance) {
        this.chestplateDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggings(ItemStack item) {
        this.leggings = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setLeggingsDropChance(float chance) {
        this.leggingsDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBoots(ItemStack item) {
        this.boots = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setBootsDropChance(float chance) {
        this.bootsDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHand(ItemStack item) {
        this.itemInHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInHandDropChance(float chance) {
        this.itemInHandDropChance = chance;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInOffHand(ItemStack item) {
        this.itemOffHand = item;
        return this;
    }

    @Override
    public EntityEquipmentBuilder setItemInOffhandDropChance(float chance) {
        this.itemOffHandDropChance = chance;
        return this;
    }

    @Override
    public ItemStack getHelmet() {
        return helmet;
    }

    @Override
    public float getHelmetDropChance() {
        return helmetDropChance;
    }

    @Override
    public ItemStack getChestplate() {
        return chestplate;
    }

    @Override
    public float getChestplateDropChance() {
        return chestplateDropChance;
    }

    @Override
    public ItemStack getLeggings() {
        return leggings;
    }

    @Override
    public float getLeggingsDropChance() {
        return leggingsDropChance;
    }

    @Override
    public ItemStack getBoots() {
        return boots;
    }

    @Override
    public float getBootsDropChance() {
        return bootsDropChance;
    }

    @Override
    public ItemStack getItemInHand() {
        return itemInHand;
    }

    @Override
    public float getItemInHandDropChance() {
        return itemInHandDropChance;
    }

    @Override
    public ItemStack getItemInOffHand() {
        return itemOffHand;
    }

    @Override
    public float getItemInOffhandDropChance() {
        return itemOffHandDropChance;
    }

    @Override
    public void apply(@NotNull LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null : "Unexpected equipment is null? of " + entity;
        equipment.setHelmet(getHelmet());
        equipment.setHelmetDropChance(getHelmetDropChance());
        equipment.setChestplate(getChestplate());
        equipment.setChestplateDropChance(getChestplateDropChance());
        equipment.setLeggings(getLeggings());
        equipment.setLeggingsDropChance(getLeggingsDropChance());
        equipment.setBoots(getBoots());
        equipment.setBootsDropChance(getBootsDropChance());
        if (Universal.getAcrylicPlugin().getVersionStore().isLegacyVersion()) {
            equipment.setItemInHand(getItemInHand());
            equipment.setItemInHandDropChance(getChestplateDropChance());
        } else {
            equipment.setItemInMainHand(getItemInHand());
            equipment.setItemInMainHandDropChance(getItemInHandDropChance());
            equipment.setItemInOffHand(getItemInOffHand());
            equipment.setItemInOffHandDropChance(getItemInOffhandDropChance());
        }
    }

}
