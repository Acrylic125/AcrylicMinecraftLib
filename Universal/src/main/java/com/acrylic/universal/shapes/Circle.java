package com.acrylic.universal.shapes;

import lombok.Setter;
import org.bukkit.util.Vector;

@Setter
public class Circle extends AbstractVectorShape {

    public static final float CIRCLE_ANGLE = 360f;

    private float radius;
    private float offset;

    public Circle(float radius, int frequency) {
        super(frequency);
        this.radius = radius;
    }

    public Circle(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
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
    public Vector getAdditiveVector() {
        float rad = getRadiansBetween() * getIndex();
        double x = radius * Math.cos(rad + offset);
        double z = radius * Math.sin(rad + offset);
        return (super.shouldReuse()) ?
                getReusableVector().setX(x).setY(0).setZ(z) :
                new Vector(x, 0, z);
    }

    @Override
    public int getFullCycleIndex() {
        return (int) getFrequency();
    }
}
