package com.acrylic.universal.regions;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

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

    @Nullable
    default T getFirstRegion(@NotNull Location location) {
        return getFirstRegion(location, getRegionMap().values());
    }

    @Nullable
    default T getFirstRegion(@NotNull Location location, Collection<T> collection) {
        for (T value : collection) {
            if (value.isInRegion(location))
                return value;
        }
        return null;
    }

    @Nullable
    default List<T> getRegions(@NotNull Location location) {
        return getRegions(location, getRegionMap().values());
    }

    default List<T> getRegions(@NotNull Location location, Collection<T> collection) {
        List<T> regions = new ArrayList<>();
        for (T value : collection) {
            if (value.isInRegion(location))
                regions.add(value);
        }
        return regions;
    }

    default void iterateRegions(@NotNull Location location, @NotNull Consumer<T> regionAction) {
        iterateRegions(location, getRegionMap().values(), regionAction);
    }

    default void iterateRegions(@NotNull Location location, Collection<T> collection, @NotNull Consumer<T> regionAction) {
        for (T value : collection) {
            if (value.isInRegion(location))
                regionAction.accept(value);
        }
    }

}
