package com.acrylic.universal.animations.holograms;

import org.bukkit.Location;

import java.util.ArrayList;

public abstract class AbstractHolograms {

    private final ArrayList<AbstractHologram> holograms = new ArrayList<>();

    public void addHologram(AbstractHologram hologram) {
        holograms.add(hologram);
    }

    public void removeHologram(AbstractHologram hologram) {
        holograms.remove(hologram);
    }

    public void teleport(final Location location) {
        for (AbstractHologram hologram : holograms)
            hologram.teleport(location);
    }

}
