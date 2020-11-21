package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public class Hologram extends AbstractHologram {

    public Hologram(Location location, String text) {
        this(location, text, 0);
    }

    public Hologram(Location location, String text, float height) {
        super(new ArmorStandAnimator(location).asHologram().name(text));
        setOffsetHeight(height);
    }

    public Hologram(AbstractArmorStandAnimator entityAnimator) {
        super(entityAnimator);
    }

    public Hologram(AbstractArmorStandAnimator entityAnimator, float height) {
        super(entityAnimator, height);
    }

    public Hologram(AbstractArmorStandAnimator entityAnimator, @Nullable String name, float height) {
        super(entityAnimator, name, height);
    }
}
