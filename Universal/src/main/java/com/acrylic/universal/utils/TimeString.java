package com.acrylic.universal.utils;

import com.acrylic.time.Time;
import com.acrylic.universal.text.ChatUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Adapted from {@link com.acrylic.time.TimeString}
 */
public final class TimeString {

    private final long ref;
    private final TimeConverter timeConverter;

    /** Modifiable **/
    private int startingTimeUnitOrdinal;
    private int endingTimeUnitOrdinal;
    private boolean shouldIncludeZeroTime = false;
    private String zero;

    /** Temporary **/
    private long current = 0;
    private boolean hasPassedInitial = false;

    public TimeString(@NotNull TimeConverter timeConverter, long ref, @NotNull Time referenceUnit) {
        this.ref = referenceUnit.toNano(ref);
        this.timeConverter = timeConverter;
        startingTimeUnitOrdinal = timeConverter.startingTimeUnitOrdinal;
        endingTimeUnitOrdinal = timeConverter.endingTimeUnitOrdinal;
        zero = timeConverter.getZero();
    }

    public int getStartingTimeUnitOrdinal() {
        return startingTimeUnitOrdinal;
    }

    public TimeString setStartingTimeUnitOrdinal(int startingTimeUnitOrdinal) {
        this.startingTimeUnitOrdinal = startingTimeUnitOrdinal;
        return this;
    }

    public int getEndingTimeUnitOrdinal() {
        return endingTimeUnitOrdinal;
    }

    public TimeString setEndingTimeUnitOrdinal(int endingTimeUnitOrdinal) {
        this.endingTimeUnitOrdinal = endingTimeUnitOrdinal;
        return this;
    }

    public boolean isShouldIncludeZeroTime() {
        return shouldIncludeZeroTime;
    }

    public TimeString setShouldIncludeZeroTime(boolean shouldIncludeZeroTime) {
        this.shouldIncludeZeroTime = shouldIncludeZeroTime;
        return this;
    }

    public String getZero() {
        return zero;
    }

    public TimeString setZero(String zero) {
        this.zero = zero;
        return this;
    }

    private String getSuffix(Time timeUnit) {
        switch (timeUnit) {
            case DAYS: return timeConverter.getDaysSuffix();
            case HOURS: return timeConverter.getHoursSuffix();
            case MINUTES: return timeConverter.getMinsSuffix();
            case SECONDS: return timeConverter.getSecondsSuffix();
            case MILLISECONDS: return timeConverter.getMillisecondsSuffix();
            case MICROSECONDS: return timeConverter.getMicrosecondsSuffix();
            case NANOSECONDS: return timeConverter.getNanoSecondsSuffix();
            default:
                return "";
        }
    }

    private boolean appendHelper(StringBuilder stringBuilder, Time timeUnit) {
        int ordinal = timeUnit.ordinal();
        if (ordinal >= startingTimeUnitOrdinal) {
            double scale = timeUnit.getNanoScale();
            long count = (long) Math.floor(current / scale);
            current -= count * scale;
            if (shouldIncludeZeroTime || count > 0) {
                if (!hasPassedInitial)
                    hasPassedInitial = true;
                else
                    stringBuilder.append(timeConverter.getSpacing()).append("&r");
                stringBuilder
                        .append(timeConverter.getValueFormat())
                        .append(count)
                        .append(timeConverter.getSuffixFormat())
                        .append(getSuffix(timeUnit));
            }
        }
        return ordinal >= endingTimeUnitOrdinal;
    }

    @Override
    public String toString() {
        if (ref == 0L) return timeConverter.getValueFormat() + zero + "&r";
        StringBuilder stringBuilder = new StringBuilder();
        current = ref;
        for (Time unit : Time.values())
            if (appendHelper(stringBuilder, unit))
                break;
        return ChatUtils.get(stringBuilder.toString());
    }

}
