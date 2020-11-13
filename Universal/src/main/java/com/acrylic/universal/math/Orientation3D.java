package com.acrylic.universal.math;

import lombok.Getter;
import org.bukkit.util.Vector;

@Getter
public class Orientation3D extends Orientation2D {

    private final TrigonometrySet yOrientation;

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

    public Orientation3D setYOrientation(TrigonometrySet trigonometrySet) {
        yOrientation.cloneFrom(trigonometrySet);
        return this;
    }

    @Override
    public Orientation3D setZOrientation(TrigonometrySet trigonometrySet) {
        super.setZOrientation(trigonometrySet);
        return this;
    }

    @Override
    public Orientation3D setXOrientation(float degree) {
        super.setXOrientation(degree);
        return this;
    }

    public Orientation3D setYOrientation(float degree) {
        yOrientation.set(-degree);
        return this;
    }

    @Override
    public Orientation3D setZOrientation(float degree) {
        super.setZOrientation(degree);
        return this;
    }

    @Override
    public void transform(Vector vector) {
        rotateAroundAxisX(vector, getXOrientation().getSin(), getXOrientation().getCos());
        rotateAroundAxisY(vector, getYOrientation().getSin(), getYOrientation().getCos());
        rotateAroundAxisZ(vector, getZOrientation().getSin(), getZOrientation().getCos());
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
