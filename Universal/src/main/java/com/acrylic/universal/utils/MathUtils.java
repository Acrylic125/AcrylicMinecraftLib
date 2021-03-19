package com.acrylic.universal.utils;

import org.jetbrains.annotations.NotNull;

public class MathUtils {

    public static float ensurePercentageFactor(float percentage) {
        return ensureLimit(percentage, 0f, 1f);
    }

    public static float ensurePercentage(float percentage) {
        return ensureLimit(percentage, 0f, 100f);
    }

    public static float ensureLimit(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }

    public static double ensureLimit(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public static int ensureLimit(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);
    }

    public static long ensureLimit(long value, long min, long max) {
        return Math.max(Math.min(value, max), min);
    }

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
            return Long.parseLong(arg);
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

    public static boolean isStringANumber(@NotNull String str) {
        try {
            Double.parseDouble(str); //To test
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isStringAnInteger(@NotNull String str) {
        try {
            Long.parseLong(str); //To test
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    

}
