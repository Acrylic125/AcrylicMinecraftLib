package com.acrylic.universal.utils;

import com.acrylic.time.Time;

/**
 * Adapted from {@link com.acrylic.time.TimeConverter}
 */
public final class TimeConverter extends TextTemplate {

    public static TimeConverter GLOBAL = new TimeConverter();
    public static TimeConverter CONSOLE = new TimeConverter();

    static {
        GLOBAL.setValueFormat("&f");
        GLOBAL.setSuffixFormat("&7");
    }

    private String spacing = " ";
    private String daysSuffix = "d";
    private String hoursSuffix = "h";
    private String minsSuffix = "min";
    private String secondsSuffix = "s";
    private String millisecondsSuffix = "ms";
    private String microsecondsSuffix = "\u03BCs";
    private String nanoSecondsSuffix = "ns";
    private boolean shouldIncludeZeroTime = false;
    private String zero = "NOW";
    protected int startingTimeUnitOrdinal = Time.DAYS.ordinal();
    protected int endingTimeUnitOrdinal = Time.NANOSECONDS.ordinal();

    public String getSpacing() {
        return spacing;
    }

    public TimeConverter setSpacing(String spacing) {
        this.spacing = spacing;
        return this;
    }

    public String getDaysSuffix() {
        return daysSuffix;
    }

    public TimeConverter setDaysSuffix(String daysSuffix) {
        this.daysSuffix = daysSuffix;
        return this;
    }

    public String getHoursSuffix() {
        return hoursSuffix;
    }

    public TimeConverter setHoursSuffix(String hoursSuffix) {
        this.hoursSuffix = hoursSuffix;
        return this;
    }

    public String getMinsSuffix() {
        return minsSuffix;
    }

    public TimeConverter setMinsSuffix(String minsSuffix) {
        this.minsSuffix = minsSuffix;
        return this;
    }

    public String getSecondsSuffix() {
        return secondsSuffix;
    }

    public TimeConverter setSecondsSuffix(String secondsSuffix) {
        this.secondsSuffix = secondsSuffix;
        return this;
    }

    public String getMillisecondsSuffix() {
        return millisecondsSuffix;
    }

    public TimeConverter setMillisecondsSuffix(String millisecondsSuffix) {
        this.millisecondsSuffix = millisecondsSuffix;
        return this;
    }

    public String getMicrosecondsSuffix() {
        return microsecondsSuffix;
    }

    public TimeConverter setMicrosecondsSuffix(String microsecondsSuffix) {
        this.microsecondsSuffix = microsecondsSuffix;
        return this;
    }

    public String getNanoSecondsSuffix() {
        return nanoSecondsSuffix;
    }

    public TimeConverter setNanoSecondsSuffix(String nanoSecondsSuffix) {
        this.nanoSecondsSuffix = nanoSecondsSuffix;
        return this;
    }

    public boolean isShouldIncludeZeroTime() {
        return shouldIncludeZeroTime;
    }

    public TimeConverter setShouldIncludeZeroTime(boolean shouldIncludeZeroTime) {
        this.shouldIncludeZeroTime = shouldIncludeZeroTime;
        return this;
    }

    public String getZero() {
        return zero;
    }

    public TimeConverter setZero(String zero) {
        this.zero = zero;
        return this;
    }

    public TimeString convert(long ref, Time refTimeUnit) {
        return new TimeString(this, ref, refTimeUnit);
    }

    public TimeString convert(long ref) {
        return convert(ref, Time.MILLISECONDS);
    }
}
