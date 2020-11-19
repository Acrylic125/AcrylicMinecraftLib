package com.acrylic.version_1_16.entity;

import com.acrylic.universal.entityanimations.AbstractEntityEquipmentBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EntityEquipmentBuilder implements AbstractEntityEquipmentBuilder {

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack itemInHand;
    private ItemStack itemOffHand;

    public EntityEquipmentBuilder setOffhandItem(ItemStack item) {
        this.itemOffHand = item;
        return this;
    }

    public ItemStack getOffhandItem() {
        return itemOffHand;
    }

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
    public void apply(@NotNull LivingEntity entity) {
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null : "Unexpected equipment is null? of " + entity;
        equipment.setHelmet(getHelmet());
        equipment.setChestplate(getChestplate());
        equipment.setLeggings(getLeggings());
        equipment.setBoots(getBoots());
        equipment.setItemInMainHand(getItemInHand());
        equipment.setItemInOffHand(getOffhandItem());
    }

}
