package com.acrylic.universal.regions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface RegionMap<T extends Region> {

    @NotNull
    Map<UUID, T> getRegionMap();

    @Nullable
    default T getRegion(@NotNull UUID uuid) {
        return getRegionMap().get(uuid);
    }

    default void addRegion(@NotNull T region) {
        getRegionMap().put(region.getId(), region);
    }

}
