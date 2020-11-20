package com.acrylic.universal.entityanimations.entities;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AbstractArmorStandAnimator extends LivingEntityAnimator {

    AbstractArmorStandAnimator marker(boolean b);

    AbstractArmorStandAnimator small(boolean b);

    AbstractArmorStandAnimator basePlate(boolean b);

    AbstractArmorStandAnimator arms(boolean b);

    AbstractArmorStandAnimator gravity(boolean b);

    AbstractArmorStandAnimator rightArmPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle);

    @Override
    default AbstractArmorStandAnimator ai(boolean b) {
        LivingEntityAnimator.super.ai(b);
        return this;
    }

    @Override
    AbstractArmorStandAnimator visible(boolean b);

    @Override
    default AbstractArmorStandAnimator name(@Nullable String name) {
        LivingEntityAnimator.super.name(name);
        return this;
    }

    @Override
    default AbstractArmorStandAnimator nameVisible(boolean b) {
        LivingEntityAnimator.super.nameVisible(b);
        return this;
    }

    @Override
    ArmorStand getBukkitEntity();

    default AbstractArmorStandAnimator asAnimator() {
        return visible(false).arms(false).basePlate(false).gravity(false);
    }

    default AbstractArmorStandAnimator asHologram() {
        return asAnimator().marker(true).small(true);
    }

}
