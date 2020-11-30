package com.acrylic.universal.regions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleRegionMap implements RegionMap<SimpleRegion> {

    private final Map<UUID, SimpleRegion> regionMap = new HashMap<>();

    @NotNull
    @Override
    public Map<UUID, SimpleRegion> getRegionMap() {
        return regionMap;
    }
}
