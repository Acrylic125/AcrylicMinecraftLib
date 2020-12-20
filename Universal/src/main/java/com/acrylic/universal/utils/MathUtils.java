package com.acrylic.universal.utils;

public class MathUtils {

    public static double round(double val, int dp) {
        double d = Math.pow(10, dp);
        return Math.round(val * d) / d;
    }

}
