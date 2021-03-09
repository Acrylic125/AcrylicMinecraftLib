package com.acrylic.universal.entity.impl;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entity.LivingEntityInstance;
import com.acrylic.universal.entity.equipment.EntityEquipmentBuilder;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.equipment.EntityEquipmentBuilder.cloneEquipment;

public interface BukkitLivingEntityInstance
        extends BukkitEntityInstance, LivingEntityInstance {

    default void setAI(boolean ai) {
        if (Universal.getAcrylicPlugin().getVersionStore().isLegacyVersion()) {
            NBTEntity nbtEntity = new NBTEntity(getBukkitEntity());
            nbtEntity.setByte("NoAI", (byte) ((ai) ? 0 : 1));
        } else
            getBukkitEntity().setAI(ai);
    }

    @Override
    default void setVisible(boolean visible) {
        if (Universal.getAcrylicPlugin().getVersionStore().isLegacyVersion()) {
            if (visible)
                getBukkitEntity().removePotionEffect(PotionEffectType.INVISIBILITY);
            else
                getBukkitEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 1, false, false));
        } else {
            getBukkitEntity().setInvisible(!visible);
        }
    }

    @Override
    default void setMaxHealth(double maxHealth) {
        AttributeInstance attribute = getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            attribute.setBaseValue(maxHealth);
        }
    }

    @Override
    default float getMaxHealth() {
        AttributeInstance attribute = getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        return (attribute != null) ? (float) attribute.getValue() : 0;
    }

    @Override
    default void setHealth(double health) {
        getBukkitEntity().setHealth(Math.min(health, getMaxHealth()));
    }

    @Override
    default void setEquipment(@NotNull EntityEquipmentBuilder equipment) {
        equipment.apply(getBukkitEntity());
    }

    @Override
    default void setEquipment(@NotNull EntityEquipment equipment) {
        EntityEquipment entityEquipment = getBukkitEntity().getEquipment();
        if (entityEquipment != null)
            cloneEquipment(equipment, entityEquipment);
    }

}
