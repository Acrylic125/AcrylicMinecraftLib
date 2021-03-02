package com.acrylic.universal.geometry;

import com.acrylic.universal.math.Orientation3D;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Circle
        implements IndexedRotatableGeometry {

    public static final float CIRCLE_ANGLE = 360f;

    private Location source = null;
    private Orientation3D orientation3D = Orientation3D.generateDefault();
    private float radius = 0, frequency = 0, angleOffset;
    private float radiansBetween = 0;
    private Vector reusableVector;

    public Circle() {
        this(1, 36);
    }

    public Circle(float radius, int frequency) {
        setRadius(radius);
        setFrequency(frequency);
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
        float rad = radiansBetween * index;
        float radius = getRadius();
        float offset = getAngleOffset();
        double cos = Math.cos(rad + offset);
        double sin = Math.sin(rad + offset);
        double x = radius * cos;
        double z = radius * sin;
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

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        this.radiansBetween = getRadiansBetween();
    }

    public float getAngleOffset() {
        return angleOffset;
    }

    public void setAngleOffset(float angleOffset) {
        this.angleOffset = angleOffset;
    }

    public float getAnglesBetween() {
        return CIRCLE_ANGLE / frequency;
    }

    public float getRadiansBetween() {
        return (float) Math.toRadians(getAnglesBetween());
    }

}
