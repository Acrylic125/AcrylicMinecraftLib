package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.entityanimations.EntityAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractHeadRotationAnimation extends EntityAnimation {

    private float yawFrequency = 15;

    public AbstractHeadRotationAnimation(EntityAnimator entityAnimator) {
        super(entityAnimator);
    }

    public void setYawFrequency(float yawFrequency) {
        this.yawFrequency = yawFrequency;
    }

    public float getYawFrequency() {
        return yawFrequency;
    }

    @Override
    public int getFullCycleIndex() {
        return 1;
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        location.setYaw(getEntityAnimator().getBukkitEntity().getLocation().getYaw() + yawFrequency);
        return location;
    }

}