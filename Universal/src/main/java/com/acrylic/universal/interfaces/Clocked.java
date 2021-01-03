package com.acrylic.universal.interfaces;

import com.acrylic.universal.utils.TimeConverter;
import org.jetbrains.annotations.NotNull;

public interface Clocked extends Timable {

    default void clockTime() {
        setTime(System.currentTimeMillis());
    }

    default long getTimeFromLastTimed() {
        return System.currentTimeMillis() - getTime();
    }

    @Override
    default long getTimeDifference() {
        return Math.abs(getTimeFromLastTimed());
    }

    @NotNull
    @Override
    default String getTimeDifferenceAsConsoleString() {
        return TimeConverter.CONSOLE.convert(getTimeFromLastTimed()).toString();
    }

    @NotNull
    @Override
    default String getTimeDifferenceAsString() {
        return TimeConverter.CONSOLE.convert(getTimeFromLastTimed()).toString();
    }

}
