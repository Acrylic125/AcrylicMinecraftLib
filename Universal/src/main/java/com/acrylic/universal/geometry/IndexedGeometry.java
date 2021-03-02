package com.acrylic.universal.geometry;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface IndexedGeometry extends Geometry {

    @NotNull
    Location getLocationAtIndex(int index);

    default Collection<Location> getLocationsToIndex(int toIndex) {
        return getLocationsFromAndToIndexWithIncrement(0, toIndex, 1);
    }

    default Collection<Location> getLocationsFromAndToIndex(int fromIndex, int toIndex) {
        return getLocationsFromAndToIndexWithIncrement(fromIndex, toIndex, 1);
    }

    default Collection<Location> getLocationsFromAndToIndexWithIncrement(int fromIndex, int toIndex, int indexIncrement) {
        List<Location> locations = new ArrayList<>();
        iterateFromAndToIndexWithIncrement(locations::add, fromIndex, toIndex, indexIncrement);
        return locations;
    }

    default void iterateToIndex(@NotNull Consumer<Location> action, int toIndex) {
        iterateFromAndToIndex(action, 0, toIndex);
    }

    default void iterateFromAndToIndex(@NotNull Consumer<Location> action, int fromIndex, int toIndex) {
        iterateFromAndToIndexWithIncrement(action, fromIndex, toIndex, 1);
    }

    default void iterateToIndexWithIncrement(@NotNull Consumer<Location> action, int toIndex, int incrementIndex) {
        iterateFromAndToIndexWithIncrement(action, 0, toIndex, incrementIndex);
    }

    default void iterateFromAndToIndexWithIncrement(@NotNull Consumer<Location> action, int fromIndex, int toIndex, int incrementIndex) {
        validateSourceLocation();
        for (int i = fromIndex; i <= toIndex; i += incrementIndex)
            action.accept(getLocationAtIndex(i));
    }

}
