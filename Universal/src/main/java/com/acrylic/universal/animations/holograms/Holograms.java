package com.acrylic.universal.animations.holograms;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Holograms extends AbstractHolograms {

    @Override
    public void addHologram(@NotNull final Location location, String text, float offsetHeight) {
        addHologram(new Hologram(location, text, offsetHeight));
    }

    @Override
    public void addHologram(AbstractHologram hologram, float offsetHeight) {
        hologram.setOffsetHeight(offsetHeight);
        addHologram(hologram);
    }
}
