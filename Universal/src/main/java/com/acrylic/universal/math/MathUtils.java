package com.acrylic.universal.math;

import org.jetbrains.annotations.NotNull;

public final class MathUtils {

    public static double round(double a, short dp) {
        double amplifier = Math.pow(10, dp);
        return Math.round(a * amplifier) / amplifier;
    }

    public static float round(float a, short dp) {
        return (float) round((double) a, dp);
    }

    public static void validateNonZero(double v) {
        validateNonZero(v, new IllegalArgumentException("The provided value cannot be 0!"));
    }

    public static <T extends Throwable> void validateNonZero(double v, @NotNull T throwable) throws T {
        if (v == 0)
            throw throwable;
    }

}
