package com.acrylic.universal.math;

/**
 * A collection of trigonometric functions cached.
 *
 * This should be used instead of recalculating trigonometric functions
 * to save on performance.
 */
public final class TrigonometrySet {

    private float sin;
    private float cos;

    public TrigonometrySet(float angle) {
        this(angle, angle);
    }

    public TrigonometrySet(float sinAngle, float cosAngle) {
        setSinByAngle(sinAngle).setCosByAngle(cosAngle);
    }

    public void cloneFrom(TrigonometrySet trigonometrySet) {
        setRawSin(trigonometrySet.getSin()).setRawCos(trigonometrySet.getCos());
    }

    public TrigonometrySet setRawSin(float sin) {
        this.sin = sin;
        return this;
    }

    public TrigonometrySet setRawCos(float cos) {
        this.cos = cos;
        return this;
    }

    public TrigonometrySet setSinByAngle(double angle) {
        return setSinByRadian(Math.toRadians(angle));
    }

    public TrigonometrySet setSinByRadian(double radian) {
        return setRawSin((float) Math.sin(radian));
    }

    public TrigonometrySet setCosByAngle(float angle) {
        return setCosByRadian(Math.toRadians(angle));
    }

    public TrigonometrySet setCosByRadian(double radian) {
        return setRawCos((float) Math.cos(radian));
    }

    public TrigonometrySet setSinCosByAngle(float angle) {
        return setSinByAngle(angle).setCosByAngle(angle);
    }

    public TrigonometrySet setSinCosByRadian(float radian) {
        return setSinByRadian(radian).setCosByRadian(radian);
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
