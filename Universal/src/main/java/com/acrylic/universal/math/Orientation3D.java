package com.acrylic.universal.math;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * This object handles the transformation of vectors
 * in 3D space.
 */
public class Orientation3D extends Orientation2D {

    private final TrigonometrySet yOrientation;

    public static Orientation3D generateDefault() {
        return new Orientation3D(0, 0, 0);
    }

    public Orientation3D(TrigonometrySet xOrientation, TrigonometrySet yOrientation, TrigonometrySet zOrientation) {
        super(xOrientation, zOrientation);
        this.yOrientation = yOrientation;
    }

    public Orientation3D(float xOrientation, float yOrientation, float zOrientation) {
        this(new TrigonometrySet(xOrientation), new TrigonometrySet(-yOrientation), new TrigonometrySet(zOrientation));
    }

    @Override
    public Orientation3D setXOrientation(TrigonometrySet trigonometrySet) {
        super.setXOrientation(trigonometrySet);
        return this;
    }

    @Override
    public Orientation3D setXOrientation(float degree) {
        super.setXOrientation(degree);
        return this;
    }

    public Orientation3D setYOrientation(TrigonometrySet trigonometrySet) {
        yOrientation.cloneFrom(trigonometrySet);
        return this;
    }

    public Orientation3D setYOrientation(float degree) {
        yOrientation.setSinCosByAngle(-degree);
        return this;
    }

    public TrigonometrySet getYOrientation() {
        return yOrientation;
    }

    @Override
    public Orientation3D setZOrientation(TrigonometrySet trigonometrySet) {
        super.setZOrientation(trigonometrySet);
        return this;
    }

    @Override
    public Orientation3D setZOrientation(float degree) {
        super.setZOrientation(degree);
        return this;
    }

    /**
     * @param vector The vector to transform.
     */
    @Override
    public void transform(@NotNull Vector vector) {
        rotateAroundAxisX(vector, getXOrientation().getCos(), getXOrientation().getSin());
        rotateAroundAxisY(vector, getYOrientation().getCos(), getYOrientation().getSin());
        rotateAroundAxisZ(vector, getZOrientation().getCos(), getZOrientation().getSin());
    }

    public void cloneFrom(Orientation3D orientation3D) {
        setXOrientation(orientation3D.getXOrientation());
        setYOrientation(orientation3D.getYOrientation());
        setZOrientation(orientation3D.getZOrientation());
    }

    public static void rotateAroundAxisX(Vector vector, double cos, double sin) {
        double y = vector.getY() * cos - vector.getZ() * sin;
        double z = vector.getY() * sin + vector.getZ() * cos;
        vector.setY(y);
        vector.setZ(z);
    }

    public static void rotateAroundAxisY(Vector vector, double cos, double sin) {
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;
        vector.setX(x);
        vector.setZ(z);
    }

    public static void rotateAroundAxisZ(Vector vector, double cos, double sin) {
        double x = vector.getX() * cos - vector.getY() * sin;
        double y = vector.getX() * sin + vector.getY() * cos;
        vector.setX(x);
        vector.setY(y);
    }

}
