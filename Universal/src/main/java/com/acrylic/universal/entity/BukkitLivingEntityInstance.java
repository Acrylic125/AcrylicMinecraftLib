package com.acrylic.universal.entity;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entityanimations.equipment.EntityEquipmentBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.inventory.EntityEquipment;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.equipment.EntityEquipmentBuilder.cloneEquipment;

public interface BukkitLivingEntityInstance
        extends BukkitEntityInstance, LivingEntityInstance {

    @Override
    default void setVisible(boolean visible) {
        getBukkitEntity().setInvisible(!visible);
    }

    @Override
    default void setMaxHealth(double maxHealth) {
        AttributeInstance attribute = getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute != null) {
            attribute.setBaseValue(maxHealth);
        }
    }

    @Override
    default double getMaxHealth() {
        AttributeInstance attribute = getBukkitEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        return (attribute != null) ? attribute.getValue() : 0;
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
