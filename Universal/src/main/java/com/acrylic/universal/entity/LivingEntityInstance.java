package com.acrylic.universal.entity;

import com.acrylic.universal.entity.equipment.EntityEquipmentBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.jetbrains.annotations.NotNull;

public interface LivingEntityInstance extends EntityInstance {

    @Override
    default void asAnimator() {
        EntityInstance.super.asAnimator();
        setAI(false);
        setVisible(false);
    }

    @NotNull
    @Override
    LivingEntity getBukkitEntity();

    void setAI(boolean ai);

    void setVisible(boolean visible);

    void setMaxHealth(double maxHealth);

    float getMaxHealth();

    void setHealth(double health);

    void setEquipment(@NotNull EntityEquipmentBuilder equipment);

    void setEquipment(@NotNull EntityEquipment equipment);

}
