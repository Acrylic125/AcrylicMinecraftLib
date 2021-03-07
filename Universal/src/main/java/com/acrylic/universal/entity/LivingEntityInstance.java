package com.acrylic.universal.entity;

import com.acrylic.universal.entityanimations.equipment.EntityEquipmentBuilder;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.jetbrains.annotations.NotNull;

public interface LivingEntityInstance extends EntityInstance {

    @NotNull
    @Override
    LivingEntity getBukkitEntity();

    void setVisible(boolean visible);

    void setMaxHealth(double maxHealth);

    double getMaxHealth();

    void setHealth(double health);

    void setEquipment(@NotNull EntityEquipmentBuilder equipment);

    void setEquipment(@NotNull EntityEquipment equipment);

}
