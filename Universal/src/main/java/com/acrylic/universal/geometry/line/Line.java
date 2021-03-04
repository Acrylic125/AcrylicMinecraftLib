package com.acrylic.universal.geometry.line;

import com.acrylic.universal.geometry.IndexedRotatableGeometry;
import com.acrylic.universal.math.MathUtils;
import com.acrylic.universal.math.Orientation3D;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Line
        implements IndexedRotatableGeometry {

    private Location source = null, toLocation = null;
    private Orientation3D orientation3D = Orientation3D.generateDefault();
    private float points = 1;
    private Vector reusableVector = null;

    public void setSourceAndToLocation(@Nullable Location source, @Nullable Location toLocation) {
        this.source = source;
        this.toLocation = toLocation;
        prepareLineCheck();
    }

    @Override
    public Location getSourceLocation() {
        return source;
    }

    public void setSource(@Nullable Location source) {
        this.source = source;
        prepareLineCheck();
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(@Nullable Location toLocation) {
        this.toLocation = toLocation;
        prepareLineCheck();
    }

    @NotNull
    @Override
    public Orientation3D getOrientation() {
        return orientation3D;
    }

    @Override
    public void setOrientation(@NotNull Orientation3D orientation) {
        this.orientation3D = orientation;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        MathUtils.validateNonZero(points);
        this.points = points;
    }

    public void setShouldReuseVector(boolean b) {
        this.reusableVector = (b) ? new Vector(0, 0, 0) : null;
    }

    @Nullable
    @Override
    public Vector getReusableVector() {
        return reusableVector;
    }

    @Override
    public void validateUse() {
        if (source == null || toLocation == null)
            throw new NullPointerException("The source location and the to location must be defined!");
    }

    private void prepareLineCheck() {
        if (source != null && toLocation != null) {
            prepareLine(source, toLocation);
        }
    }

    protected abstract void prepareLine(@NotNull Location source, @NotNull Location toLocation);

    public int getFullCycleIndex() {
        validateUse();
        return (int) Math.ceil(source.distance(toLocation) * getPoints());
    }

}
