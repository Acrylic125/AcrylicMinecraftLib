package com.acrylic.universal.math;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * This object handles the transformation of vectors
 * in 2D space.
 */
public class Orientation2D {

    private final TrigonometrySet xOrientation;
    private final TrigonometrySet zOrientation;

    public Orientation2D(TrigonometrySet xOrientation, TrigonometrySet zOrientation) {
        this.xOrientation = xOrientation;
        this.zOrientation = zOrientation;
    }

    public Orientation2D(float xOrientation, float zOrientation) {
        this(new TrigonometrySet(xOrientation), new TrigonometrySet(zOrientation));
    }

    public Orientation2D setXOrientation(TrigonometrySet trigonometrySet) {
        xOrientation.cloneFrom(trigonometrySet);
        return this;
    }

    public Orientation2D setXOrientation(float degree) {
        xOrientation.setSinCosByAngle(degree);
        return this;
    }

    public TrigonometrySet getXOrientation() {
        return xOrientation;
    }

    public Orientation2D setZOrientation(TrigonometrySet trigonometrySet) {
        zOrientation.cloneFrom(trigonometrySet);
        return this;
    }

    public Orientation2D setZOrientation(float degree) {
        xOrientation.setSinCosByAngle(degree);
        return this;
    }

    public TrigonometrySet getZOrientation() {
        return zOrientation;
    }

    /**
     * @param vector The vector to transform.
     */
    public void transform(@NotNull Vector vector) {
        rotateAroundAxisX(vector, xOrientation.getCos(), xOrientation.getSin());
        rotateAroundAxisZ(vector, zOrientation.getCos(), zOrientation.getSin());
    }

    public void cloneFrom(Orientation2D orientation2D) {
        setXOrientation(orientation2D.getXOrientation());
        setZOrientation(orientation2D.getZOrientation());
    }

    public static void rotateAroundAxisX(Vector vector, double cos, double sin) {
        double x = vector.getX() * cos - vector.getY() * sin;
        vector.setX(x);
    }

    public static void rotateAroundAxisZ(Vector vector, double cos, double sin) {
        double z = vector.getZ() * cos + vector.getX() * sin;
        vector.setZ(z);
    }

}
