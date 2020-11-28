package com.acrylic.universal.math;

public final class MathUtils {

    public static double round(double a, short dp) {
        double amplifier = Math.pow(10, dp);
        return Math.round(a * amplifier) / amplifier;
    }

    public static float round(float a, short dp) {
        return (float) round((double) a, dp);
    }

}
