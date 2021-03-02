package com.acrylic.universal.geometry.line;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class LinearLine extends Line {

    private Vector deltaVector;

    @NotNull
    @Override
    public Vector getDeltaVector(int index) {
        return deltaVector.clone().multiply(index);
    }

    @Override
    public void setPoints(float points) {
        super.setPoints(points);
        Location source = getSourceLocation(), toLocation = getToLocation();
        if (source != null && toLocation != null)
            prepareLine(source, toLocation);
    }

    @Override
    protected void prepareLine(@NotNull Location source, @NotNull Location toLocation) {
        this.deltaVector = (toLocation.toVector().add(source.toVector().multiply(-1))).multiply(1 / getPoints());
    }
}
