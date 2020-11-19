package com.acrylic.universal.entityanimations;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AbstractArmorStandAnimator extends EntityAnimator {

    AbstractArmorStandAnimator marker(boolean b);

    AbstractArmorStandAnimator small(boolean b);

    AbstractArmorStandAnimator basePlate(boolean b);

    AbstractArmorStandAnimator visible(boolean b);

    AbstractArmorStandAnimator arms(boolean b);

    AbstractArmorStandAnimator gravity(boolean b);

    AbstractArmorStandAnimator rightArmPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle);

    AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle);

    @Override
    default AbstractArmorStandAnimator name(@Nullable String name) {
        return (AbstractArmorStandAnimator) EntityAnimator.super.name(name);
    }

    @Override
    default AbstractArmorStandAnimator nameVisible(boolean b) {
        return (AbstractArmorStandAnimator) EntityAnimator.super.nameVisible(b);
    }

    @Override
    ArmorStand getBukkitEntity();

    default AbstractArmorStandAnimator asAnimator() {
        return visible(false).arms(false).basePlate(false).gravity(false);
    }

}
