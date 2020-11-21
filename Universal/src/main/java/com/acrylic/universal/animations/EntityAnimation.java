package com.acrylic.universal.animations;

import com.acrylic.universal.animations.holograms.AbstractHolograms;
import com.acrylic.universal.animations.holograms.HologramSupport;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.interfaces.Deletable;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EntityAnimation
        extends Animation
        implements Deletable, HologramSupport {

    private final EntityAnimator entityAnimator;
    private AbstractHolograms holograms;
    private float offsetHeight = 0;

    public EntityAnimation(EntityAnimator entityAnimator) {
        this.entityAnimator = entityAnimator;
    }

    public void setOffsetHeight(float offsetHeight) {
        this.offsetHeight = offsetHeight;
    }

    public float getOffsetHeight() {
        return offsetHeight;
    }

    public EntityAnimator getEntityAnimator() {
        return entityAnimator;
    }

    public void teleport(@NotNull Location location) {
        if (isUsingHolograms())
            holograms.teleport(location);
        getEntityAnimator().teleport(getLocation(new Location(location.getWorld(), location.getX(), location.getY() + offsetHeight, location.getZ(), location.getYaw(), location.getPitch())));
    }

    @Override
    public void setHologram(@Nullable AbstractHolograms abstractHolograms) {
        this.holograms = abstractHolograms;
    }

    @Override
    public AbstractHolograms getHolograms() {
        return holograms;
    }

    @Override
    public void delete() {
        entityAnimator.delete();
    }

}
