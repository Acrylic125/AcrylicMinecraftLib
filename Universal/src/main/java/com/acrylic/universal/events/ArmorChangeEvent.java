package com.acrylic.universal.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmorChangeEvent extends Event implements Cancellable {

    public enum ChangeType {
        EQUIP, UN_EQUIP, CHANGE, UNKNOWN
    }

    public enum EquipType {
        ITEM_BREAK, DEATH, RIGHT_CLICK, JOIN, QUIT, INVENTORY_CLICK, INVENTORY_DRAG, UNKNOWN, DISPENSER
    }

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    private final LivingEntity entity;
    private ChangeType changeType = ChangeType.UNKNOWN;
    private EquipType equipType = EquipType.UNKNOWN;
    private ItemStack newItem, previousItem, wearingHelmet, wearingChestplate, wearingLeggings, wearingBoots;
    private ArmorType armorType;
    private boolean hotSwap = false, cancelled = false, handleEquip = false, handleUnEquip = false;

    public ArmorChangeEvent(LivingEntity entity) {
        this.entity = entity;
    }

    @NotNull
    public LivingEntity getEntity() {
        return entity;
    }

    public ArmorChangeEvent setChangeType(@NotNull ChangeType changeType) {
        this.changeType = changeType;
        return this;
    }

    @NotNull
    public ChangeType getChangeType() {
        return changeType;
    }

    public ArmorChangeEvent setEquipType(@NotNull EquipType equipType) {
        this.equipType = equipType;
        return this;
    }

    @NotNull
    public EquipType getEquipType() {
        return equipType;
    }

    public ArmorChangeEvent setNewItem(@Nullable ItemStack newItem) {
        this.newItem = newItem;
        return this;
    }

    @Nullable
    public ItemStack getNewItem() {
        return newItem;
    }

    public ArmorChangeEvent setPreviousItem(@Nullable ItemStack previousItem) {
        this.previousItem = previousItem;
        return this;
    }

    @Nullable
    public ItemStack getPreviousItem() {
        return previousItem;
    }

    public ArmorChangeEvent setArmorType(ArmorType armorType) {
        this.armorType = armorType;
        return this;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public ArmorChangeEvent setHotSwap(boolean hotSwap) {
        this.hotSwap = hotSwap;
        return this;
    }

    public boolean isHotSwap() {
        return hotSwap;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public boolean handleEquip() {
        return handleEquip;
    }

    public boolean handleUnEquip() {
        return handleUnEquip;
    }

    public void callEvent() {
        boolean isChange = changeType.equals(ChangeType.CHANGE);
        handleEquip = isChange || changeType.equals(ChangeType.EQUIP);
        handleUnEquip = isChange || changeType.equals(ChangeType.UN_EQUIP);
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) {
            wearingHelmet = equipment.getHelmet();
            wearingChestplate = equipment.getChestplate();
            wearingLeggings = equipment.getLeggings();
            wearingBoots = equipment.getBoots();
            if (armorType != null) {
                switch (armorType) {
                    case HELMET:
                        wearingHelmet = newItem;
                        break;
                    case CHESTPLATE:
                        wearingChestplate = newItem;
                        break;
                    case LEGGINGS:
                        wearingLeggings = newItem;
                        break;
                    case BOOTS:
                        wearingBoots = newItem;
                        break;
                }
            }
        }
        Bukkit.getPluginManager().callEvent(this);
    }

    @Nullable
    public ItemStack getWearingHelmet() {
        return wearingHelmet;
    }

    @Nullable
    public ItemStack getWearingChestplate() {
        return wearingChestplate;
    }

    @Nullable
    public ItemStack getWearingLeggings() {
        return wearingLeggings;
    }

    @Nullable
    public ItemStack getWearingBoots() {
        return wearingBoots;
    }
}
