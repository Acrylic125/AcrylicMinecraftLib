package com.acrylic.universal.geometry;

public class GeometryOptions {

    public static GeometryOptions generateDefault() {
        return new GeometryOptions();
    }

    private boolean shouldRecycleLocations = true;

    public boolean isShouldRecycleLocations() {
        return shouldRecycleLocations;
    }

    public GeometryOptions setShouldRecycleLocations(boolean shouldRecycleLocations) {
        this.shouldRecycleLocations = shouldRecycleLocations;
        return this;
    }
}
