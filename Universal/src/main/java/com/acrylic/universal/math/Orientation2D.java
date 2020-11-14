package com.acrylic.universal.math;

import lombok.Getter;
import org.bukkit.util.Vector;

@Getter
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

    public Orientation2D setZOrientation(TrigonometrySet trigonometrySet) {
        zOrientation.cloneFrom(trigonometrySet);
        return this;
    }

    public Orientation2D setXOrientation(float degree) {
        xOrientation.set(degree);
        return this;
    }

    public Orientation2D setZOrientation(float degree) {
        xOrientation.set(degree);
        return this;
    }

    public void transform(Vector vector) {
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
