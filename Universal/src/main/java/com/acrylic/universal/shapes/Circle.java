package com.acrylic.universal.shapes;

import org.bukkit.util.Vector;

public class Circle extends AbstractVectorShape {

    public static final float CIRCLE_ANGLE = 360f;

    public Circle(float radius, int amount) {
        super(radius, amount);
    }

    public Circle(float radius, int amount, float xRot, float yRot, float zRot) {
        super(radius, amount, xRot, yRot, zRot);
    }

    public float getAnglesBetween() {
        return CIRCLE_ANGLE / getAmount();
    }

    public float getRadiansBetween() {
        return (float) Math.toRadians(getAnglesBetween());
    }

    @Override
    public Vector getAdditiveVector() {
        float rad = getRadiansBetween() * getIndex();
        double x = getRadius() * Math.cos(rad);
        double z = getRadius() * Math.sin(rad);
        return new Vector(x, 0, z);
    }
}
