package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHologram extends EntityAnimation {

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator) {
        this(entityAnimator, 0);
    }

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator, float offsetHeight) {
        super(entityAnimator);
        setOffsetHeight(offsetHeight);
    }

    public AbstractHologram(AbstractArmorStandAnimator entityAnimator, @Nullable String name, float offsetHeight) {
        super(entityAnimator);
        setOffsetHeight(offsetHeight);
        entityAnimator.name(name);
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        return location;
    }

    @Override
    public int getFullCycleIndex() {
        return 1;
    }
}
