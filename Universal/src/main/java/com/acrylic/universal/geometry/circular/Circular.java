package com.acrylic.universal.geometry.circular;

import com.acrylic.universal.geometry.IndexedRotatableGeometry;
import com.acrylic.universal.math.MathUtils;
import com.acrylic.universal.math.Orientation3D;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Circular
        implements IndexedRotatableGeometry {

    public static final float CIRCLE_ANGLE = 360f;

    private Location source = null;
    private Orientation3D orientation3D = Orientation3D.generateDefault();
    private float radius = 0, points = 0, radianOffset = 0;
    private float radianBetween = 0;
    private Vector reusableVector;

    public Circular() {
        this(1, 36);
    }

    public Circular(float radius, int points) {
        setRadius(radius);
        setPoints(points);
    }

    public void setLocation(@Nullable Location source) {
        this.source = source;
    }

    @Override
    public Location getSourceLocation() {
        return source;
    }

    public void setShouldReuseVector(boolean b) {
        this.reusableVector = (b) ? new Vector(0, 0, 0) : null;
    }

    @Nullable
    @Override
    public Vector getReusableVector() {
        return reusableVector;
    }

    @NotNull
    @Override
    public Vector getDeltaVector(int index) {
        float rad = calculateRadians(index),
                radius = calculateRadius(index);
        double cos = Math.cos(rad),
                sin = Math.sin(rad);
        double x = radius * cos,
                z = radius * sin;
        return (isReusingVector()) ?
                reusableVector.setX(x).setY(0).setZ(z) :
                new Vector(x, 0, z);
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    protected float getPoints() {
        return points;
    }

    protected void setPoints(int points) {
        MathUtils.validateNonZero(points);
        this.points = points;
        this.radianBetween = (float) Math.toRadians(getAngleBetween());
    }

    public float getRadianOffset() {
        return radianOffset;
    }

    public void setAngleOffset(float angleOffset) {
        this.radianOffset = (float) Math.toRadians(radianOffset);
    }

    public float getAngleBetween() {
        return CIRCLE_ANGLE / points;
    }

    public float getRadianBetween() {
        return radianBetween;
    }

    protected abstract float calculateRadius(int index);

    protected abstract float calculateRadians(int index);

    public abstract int getFullCycleIndex();

}
