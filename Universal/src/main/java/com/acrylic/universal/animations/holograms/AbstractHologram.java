package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHologram extends EntityAnimation {

    private float height;

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator) {
        this(entityAnimator, 0);
    }

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator, float height) {
        super(entityAnimator);
        this.height = height;
    }

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator, @Nullable String name, float height) {
        super(entityAnimator);
        this.height = height;
        entityAnimator.name(name);
    }

    public void teleport(@NotNull Location location) {
        double y = location.getY();
        location.setY(y + height);
        getEntityAnimator().teleport(location);
        location.setY(y);
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

}
