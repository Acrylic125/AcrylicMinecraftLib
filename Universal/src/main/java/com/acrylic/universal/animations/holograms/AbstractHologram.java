package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.entity.ArmorStandInstance;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHologram extends EntityAnimation {

    public AbstractHologram(ArmorStandInstance armorStandInstance) {
        this(armorStandInstance, 0);
    }

    public AbstractHologram(ArmorStandInstance armorStandInstance, float offsetHeight) {
        super(armorStandInstance);
        setOffsetHeight(offsetHeight);
    }

    public AbstractHologram(ArmorStandInstance armorStandInstance, @Nullable String name, float offsetHeight) {
        super(armorStandInstance);
        setOffsetHeight(offsetHeight);
        armorStandInstance.setName(name);
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        return location;
    }

}
