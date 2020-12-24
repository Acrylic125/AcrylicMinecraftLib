package com.acrylic.universal.regions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleRegionMap<T extends Region>
        implements RegionMap<T> {

    private final Map<UUID, T> regionMap = new HashMap<>();

    @NotNull
    @Override
    public Map<UUID, T> getRegionMap() {
        return regionMap;
    }
}
