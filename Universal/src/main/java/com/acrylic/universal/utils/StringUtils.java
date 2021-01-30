package com.acrylic.universal.utils;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class StringUtils {

    public static final Pattern COMMA_PATTERN = Pattern.compile(",");
    public static final Pattern SPACE_PATTERN = Pattern.compile(" ");
    public static final Pattern UNDERSCORE_PATTERN = Pattern.compile("_");
    public static final StringFormatter.NumberStringFormatter NUMBER_STRING_FORMATTER = new StringFormatter.NumberStringFormatter();
    public static final DecimalFormat COMMA_SEPARATED_NUMBER_FORMATTER = new DecimalFormat("###,###.##");

    private final static TreeMap<Integer, String> mapToRoman = new TreeMap<>();

    static {
        //Numeric Formatter.
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 3), "k", "thousand");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 6), "m", "million");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 9), "b", "billion");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 12), "t", "trillion");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 15), "q", "quadrillion");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 18), "quin", "quintillion");
        NUMBER_STRING_FORMATTER.addFormat(repeatStringSequence('0', 21), "s", "sextillion");
        //Roman Map.
        mapToRoman.put(1000, "M");
        mapToRoman.put(900, "CM");
        mapToRoman.put(500, "D");
        mapToRoman.put(400, "CD");
        mapToRoman.put(100, "C");
        mapToRoman.put(90, "XC");
        mapToRoman.put(50, "L");
        mapToRoman.put(40, "XL");
        mapToRoman.put(10, "X");
        mapToRoman.put(9, "IX");
        mapToRoman.put(5, "V");
        mapToRoman.put(4, "IV");
        mapToRoman.put(1, "I");
    }

    public static String toRoman(int number) {
        int l = mapToRoman.floorKey(number);
        return (number == l) ? mapToRoman.get(number) : mapToRoman.get(l) + toRoman(number-l);
    }

    public static String[] splitStringsIn2(@NotNull String str, int splitAtIndex) {
        int l = str.length() - 1, i = MathUtils.ensureLimit(splitAtIndex, 0, l);
        return (l <= -1) ?
                new String[] {"", ""}
                :
                new String[] {
                (i == 0) ? "" : str.substring(0, i),
                (i == l) ? "" : str.substring(i, l)
        };
    }

    public static String[] splitStringsIn2ByPercentage(@NotNull String str, float percentage) {
        return splitStringsIn2(str, getIndexByPercentage(str, percentage));
    }

    private static int getIndexByPercentage(@NotNull String str, float percentage) {
        return (int) Math.floor((percentage / 100f) * (str.length() - 1));
    }

    public static String repeatStringSequence(@NotNull CharSequence stringSequence, int amount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++)
            stringBuilder.append(stringSequence);
        return stringBuilder.toString();
    }

    public static String repeatStringSequence(char c, int amount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++)
            stringBuilder.append(c);
        return stringBuilder.toString();
    }

    public static String prefixSplit(@NotNull String str, int splitAtIndex, @NotNull String firstPrefix, @NotNull String secondPrefix) {
        return prefixSplit(splitStringsIn2(str, splitAtIndex), firstPrefix, secondPrefix);
    }

    public static String prefixSuffixSplit(@NotNull String str, int splitAtIndex, @NotNull String firstPrefix, @NotNull String firstSuffix, @NotNull String secondPrefix, @NotNull String secondSuffix) {
        return prefixSuffixSplit(splitStringsIn2(str, splitAtIndex), firstPrefix, firstSuffix, secondPrefix, secondSuffix);
    }

    public static String prefixSplitByPercentage(@NotNull String str, float percentage, @NotNull String firstPrefix, @NotNull String secondPrefix) {
        return prefixSplit(splitStringsIn2ByPercentage(str, percentage), firstPrefix, secondPrefix);
    }

    public static String prefixSuffixSplitByPercentage(@NotNull String str, float percentage, @NotNull String firstPrefix, @NotNull String firstSuffix, @NotNull String secondPrefix, @NotNull String secondSuffix) {
        return prefixSuffixSplit(splitStringsIn2ByPercentage(str, percentage), firstPrefix, firstSuffix, secondPrefix, secondSuffix);
    }

    private static String prefixSplit(@NotNull String[] split, @NotNull String firstPrefix, @NotNull String secondPrefix) {
        return firstPrefix + split[0] + secondPrefix + split[1];
    }

    private static String prefixSuffixSplit(@NotNull String[] split, @NotNull String firstPrefix, @NotNull String firstSuffix, @NotNull String secondPrefix, @NotNull String secondSuffix) {
        return firstPrefix + split[0] + firstSuffix + secondPrefix + split[1] + secondSuffix;
    }

    public static String prefixRepeatingSequenceByPercentage(@NotNull CharSequence stringSequence, int amount, float percentage, @NotNull String firstPrefix, @NotNull String secondPrefix) {
        return prefixSplitByPercentage(repeatStringSequence(stringSequence, amount), percentage, firstPrefix, secondPrefix);
    }

    public static String prefixRepeatingSequenceByPercentage(char c, int amount, float percentage, @NotNull String firstPrefix, @NotNull String secondPrefix) {
        return prefixSplitByPercentage(repeatStringSequence(c, amount), percentage, firstPrefix, secondPrefix);
    }

    public static String prefixSuffixRepeatingSequenceByPercentage(@NotNull CharSequence stringSequence, int amount, float percentage, @NotNull String firstPrefix, @NotNull String firstSuffix, @NotNull String secondPrefix, @NotNull String secondSuffix) {
        return prefixSuffixSplitByPercentage(repeatStringSequence(stringSequence, amount), percentage, firstPrefix, firstSuffix, secondPrefix, secondSuffix);
    }

    public static String prefixSuffixRepeatingSequenceByPercentage(char c, int amount, float percentage, @NotNull String firstPrefix, @NotNull String firstSuffix, @NotNull String secondPrefix, @NotNull String secondSuffix) {
        return prefixSuffixSplitByPercentage(repeatStringSequence(c, amount), percentage, firstPrefix, firstSuffix, secondPrefix, secondSuffix);
    }

}
