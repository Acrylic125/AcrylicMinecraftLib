package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.entity.ArmorStandInstance;
import com.acrylic.universal.entity.impl.BukkitArmorStandInstance;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public class Hologram extends AbstractHologram {

    public Hologram(Location location, String text) {
        this(location, text, 0);
    }

    public Hologram(Location location, String text, float height) {
        super(BukkitArmorStandInstance.builder(location).asHologram().name(text).buildEntityInstance());
        setOffsetHeight(height);
    }

    public Hologram(ArmorStandInstance armorStandInstance) {
        super(armorStandInstance);
    }

    public Hologram(ArmorStandInstance armorStandInstance, float height) {
        super(armorStandInstance, height);
    }

    public Hologram(ArmorStandInstance armorStandInstance, @Nullable String name, float height) {
        super(armorStandInstance, name, height);
    }
}
