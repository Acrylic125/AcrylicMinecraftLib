package com.acrylic.universal.utils;

import org.jetbrains.annotations.NotNull;

public class MathUtils {

    public static double round(double val, int dp) {
        double d = Math.pow(10, dp);
        return Math.round(val * d) / d;
    }

    public static byte getByte(@NotNull String arg, byte defaultValue) {
        try {
            return Byte.parseByte(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static short getShort(@NotNull String arg, short defaultValue) {
        try {
            return Short.parseShort(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static int getInteger(@NotNull String arg, int defaultValue) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static long getLong(@NotNull String arg, long defaultValue) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static float getFloat(@NotNull String arg, float defaultValue) {
        try {
            return Float.parseFloat(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    public static double getDouble(@NotNull String arg, double defaultValue) {
        try {
            return Double.parseDouble(arg);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

}
