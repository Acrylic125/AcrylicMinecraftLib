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

    public void cloneFrom(TrigonometrySet trigonometrySet) {
        forceSetSin(trigonometrySet.getSin()).forceSetCos(trigonometrySet.getCos());
    }

    public TrigonometrySet forceSetSin(float sin) {
        this.sin = sin;
        return this;
    }

    public TrigonometrySet forceSetCos(float cos) {
        this.cos = cos;
        return this;
    }

    public TrigonometrySet setSin(float angle) {
        return forceSetSin((float) Math.sin(Math.toRadians(angle)));
    }

    public TrigonometrySet setCos(float angle) {
        return forceSetCos((float) Math.cos(Math.toRadians(angle)));
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
