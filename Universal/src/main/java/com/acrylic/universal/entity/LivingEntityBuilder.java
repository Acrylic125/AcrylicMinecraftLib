package com.acrylic.universal.entity;

import com.acrylic.universal.entityanimations.equipment.EntityEquipmentBuilder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public abstract class LivingEntityBuilder<B extends LivingEntityBuilder<B>>
        extends EntityBuilder<B> {

    public B ai(boolean ai) {
        getBuildFrom().setAI(ai);
        return (B) this;
    }

    public B maxHealth(double maxHealth) {
        getBuildFrom().setMaxHealth(maxHealth);
        return (B) this;
    }

    public B health(double health) {
        getBuildFrom().setHealth(health);
        return (B) this;
    }

    public B equipment(@NotNull EntityEquipment equipment) {
        getBuildFrom().setEquipment(equipment);
        return (B) this;
    }

    public B equipment(@NotNull EntityEquipmentBuilder equipmentBuilder) {
        getBuildFrom().setEquipment(equipmentBuilder);
        return (B) this;
    }

    @Override
    public abstract LivingEntity buildEntity();

    @Override
    public abstract LivingEntityInstance buildEntityInstance();

    @Override
    public abstract LivingEntityInstance getBuildFrom();

}
