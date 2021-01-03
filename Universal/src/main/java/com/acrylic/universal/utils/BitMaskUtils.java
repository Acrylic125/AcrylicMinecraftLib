package com.acrylic.universal.utils;

public class BitMaskUtils {

    public static boolean isBitInMask(int bitMask, int bit) {
        return (bitMask & bit) == bit;
    }

    public static int setBitToMask(int bitMask, int bit, boolean flag) {
        return (flag) ? setBitToMask(bitMask, bit) : removeBitFromMask(bitMask, bit);
    }

    public static int setBitToMask(int bitMask, int bit) {
        return bitMask | bit;
    }

    public static int removeBitFromMask(int bitMask, int bit) {
        return bitMask & ~bit;
    }

}
