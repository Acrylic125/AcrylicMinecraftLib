package com.acrylic.universal.shapes;

import lombok.Getter;
import org.bukkit.util.Vector;

public class Circle extends AbstractVectorShape {

    public static final float CIRCLE_ANGLE = 360f;

    private float radius;
    private float offset;

    private float lastCos;
    private float lastSin;

    public Circle(float radius, int frequency) {
        super(frequency);
        this.radius = radius;
    }

    public Circle(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
        this.radius = radius;
    }

    public static float getCircleAngle() {
        return CIRCLE_ANGLE;
    }

    public float getLastCos() {
        return lastCos;
    }

    public float getLastSin() {
        return lastSin;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setOffset(float degrees) {
        this.offset = (float) Math.toRadians(degrees);
    }

    public float getOffset() {
        return offset;
    }

    public float getRadius() {
        return radius;
    }

    public float getAnglesBetween() {
        return CIRCLE_ANGLE / getFrequency();
    }

    public float getRadiansBetween() {
        return (float) Math.toRadians(getAnglesBetween());
    }

    @Override
    public Vector getAdditiveVector(int index) {
        float rad = getRadiansBetween() * index;
        float radius = getRadius();
        float offset = getOffset();
        this.lastCos = (float) Math.cos(rad + offset);
        this.lastSin = (float) Math.sin(rad + offset);
        double x = radius * lastCos;
        double z = radius * lastSin;
        return (super.shouldReuseVector()) ?
                getReusableVector().setX(x).setY(0).setZ(z) :
                new Vector(x, 0, z);
    }

    @Override
    public int getFullCycleIndex() {
        return (int) getFrequency();
    }
}
