package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.ArmorStandInstance;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitArmorStandInstance
        implements ArmorStandInstance, BukkitLivingEntityInstance {

    public static Builder builder(@NotNull Location location) {
        return new Builder(new BukkitArmorStandInstance(location));
    }

    public static Builder builder(@NotNull ArmorStand armorStand) {
        return new Builder(new BukkitArmorStandInstance(armorStand));
    }

    private final ArmorStand armorStand;

    public BukkitArmorStandInstance(@NotNull Location location) {
        this((ArmorStand) spawnEntity(location, EntityType.ARMOR_STAND));
    }

    public BukkitArmorStandInstance(@NotNull ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    @NotNull
    @Override
    public ArmorStand getBukkitEntity() {
        return armorStand;
    }

    @Override
    public boolean hasArms() {
        return armorStand.hasArms();
    }

    @Override
    public void setArms(boolean arms) {
        armorStand.setArms(arms);
    }

    @Override
    public boolean hasBasePlate() {
        return armorStand.hasBasePlate();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        armorStand.setBasePlate(basePlate);
    }

    @Override
    public boolean hasGravity() {
        return armorStand.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        armorStand.setGravity(gravity);
    }

    @Override
    public boolean isSmall() {
        return armorStand.isSmall();
    }

    @Override
    public void setSmall(boolean small) {
        armorStand.setSmall(small);
    }

    @Override
    public boolean isMarker() {
        return armorStand.isMarker();
    }

    @Override
    public void setMarker(boolean marker) {
        armorStand.setMarker(marker);
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle eulerAngle) {
        armorStand.setRightArmPose(eulerAngle);
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle eulerAngle) {
        armorStand.setLeftArmPose(eulerAngle);
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle eulerAngle) {
        armorStand.setRightArmPose(eulerAngle);
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle eulerAngle) {
        armorStand.setLeftLegPose(eulerAngle);
    }


}
