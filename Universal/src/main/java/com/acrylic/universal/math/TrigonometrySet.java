package com.acrylic.universal.math;

public final class TrigonometrySet {

    private float sin;
    private float cos;

    public TrigonometrySet(float angle) {
        this(angle, angle);
    }

    public TrigonometrySet(float sinAngle, float cosAngle) {
        setSin(sinAngle).setCos(cosAngle);
    }

    public TrigonometrySet setSin(float angle) {
        this.sin = (float) Math.sin(Math.toRadians(angle));
        return this;
    }

    public TrigonometrySet setCos(float angle) {
        this.cos = (float) Math.cos(Math.toRadians(angle));
        return this;
    }

    public TrigonometrySet set(float angle) {
        return setSin(angle).setCos(angle);
    }

    public double getAngle() {
        return Math.toDegrees(getRadian());
    }

    public double getRadian() {
        return Math.acos(cos);
    }

    public float getSin() {
        return sin;
    }

    public float getCos() {
        return cos;
    }

    public float getTan() {
        return getSin() / getCos();
    }

    public float getCosec() {
        return 1 / getSin();
    }

    public float getSec() {
        return 1 / getCos();
    }

    public float getCot() {
        return 1 / getTan();
    }

}
