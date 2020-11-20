package com.acrylic.universal.entityanimations.entities;

import com.acrylic.universal.entityanimations.EntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class ArmorStandAnimator implements AbstractArmorStandAnimator {

    private final ArmorStand bukkitEntity;

    public ArmorStandAnimator(Location location) {
        this((ArmorStand) EntityAnimator.spawnEntity(location, EntityType.ARMOR_STAND));
    }

    public ArmorStandAnimator(ArmorStand bukkitEntity) {
        this.bukkitEntity = bukkitEntity;
    }

    @Override
    public AbstractArmorStandAnimator marker(boolean b) {
        bukkitEntity.setMarker(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator small(boolean b) {
        bukkitEntity.setSmall(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator basePlate(boolean b) {
        bukkitEntity.setBasePlate(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator visible(boolean b) {
        bukkitEntity.setVisible(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator arms(boolean b) {
        bukkitEntity.setArms(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator gravity(boolean b) {
        bukkitEntity.setGravity(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator rightArmPose(@NotNull EulerAngle eulerAngle) {
        bukkitEntity.setRightArmPose(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle) {
        bukkitEntity.setLeftArmPose(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle) {
        bukkitEntity.setRightLegPose(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle) {
        bukkitEntity.setLeftLegPose(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle) {
        bukkitEntity.setHeadPose(eulerAngle);
        return this;
    }

    @Override
    public ArmorStand getBukkitEntity() {
        return bukkitEntity;
    }

}
