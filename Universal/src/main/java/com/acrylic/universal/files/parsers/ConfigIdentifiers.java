package com.acrylic.universal.files.parsers;

import java.util.regex.Pattern;

public class ConfigIdentifiers {

    /**
     * These are some usable constants that should be used to determine
     * what value is what.
     */
    public static final Pattern LONG_PATTERN = Pattern.compile("(long)");
    public static final Pattern INTEGER_PATTERN = Pattern.compile("(int)");
    public static final Pattern SHORT_PATTERN = Pattern.compile("(float)");
    public static final Pattern DOUBLE_PATTERN = Pattern.compile("(double)");
    public static final Pattern BYTE_PATTERN = Pattern.compile("(byte)");
    public static final Pattern BOOLEAN_PATTERN = Pattern.compile("(boolean)");
    public static final Pattern FLOAT_PATTERN = Pattern.compile("(float)");
    public static final Pattern WHOLE_NUMBER_PATTERN =
            Pattern.compile(LONG_PATTERN + "|" +
                    INTEGER_PATTERN + "|" +
                    SHORT_PATTERN + "|" +
                    BYTE_PATTERN);
    public static final Pattern NON_WHOLE_NUMBER_PATTERN =
            Pattern.compile(DOUBLE_PATTERN + "|" + FLOAT_PATTERN);

}
