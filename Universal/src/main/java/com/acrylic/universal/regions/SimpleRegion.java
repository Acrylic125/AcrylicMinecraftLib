package com.acrylic.universal.regions;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Regions.
 */
public class SimpleRegion implements Region {

    private final Location start;
    private final Location end;
    private final UUID id = UUID.randomUUID();

    public SimpleRegion(@NotNull Location start, @NotNull Location end) {
        this.start = start;
        this.end = end;
    }

    @NotNull
    @Override
    public Location getLocation1() {
        return start;
    }

    @NotNull
    @Override
    public Location getLocation2() {
        return end;
    }

    @NotNull
    @Override
    public UUID getId() {
        return id;
    }
}
