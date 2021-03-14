package com.acrylic.universal.geometry.line;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * f(d) = scalar(d(d - distance)) + constant
 *
 * Where:
 *   distance = |V_to - V_from|
 *   d        = index / frequency = |V_current - V_from|
 */
public class QuadraticYLine extends Line {

    private Vector deltaVector;
    private float yOffset = 0, yMultiplier = 1;

    public float getYOffset() {
        return yOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getYMultiplier() {
        return yMultiplier;
    }

    public void setYMultiplier(float yMultiplier) {
        this.yMultiplier = yMultiplier;
    }

    @NotNull
    @Override
    public Vector getDeltaVector(int index) {
        return deltaVector.clone().multiply(index)
                .setY(calculateY(index));
    }

    private double calculateY(int index) {
        return yMultiplier * (index * (index - getPoints())) + yOffset;
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
