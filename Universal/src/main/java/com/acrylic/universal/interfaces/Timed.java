package com.acrylic.universal.interfaces;

import com.acrylic.universal.utils.TimeConverter;
import org.jetbrains.annotations.NotNull;

public interface Timed extends Timable {

    default boolean hasPassedTimed() {
        return System.currentTimeMillis() >= getTime();
    }

    default long getTimeLeft() {
        return getTime() - System.currentTimeMillis();
    }

    @Override
    default long getTimeDifference() {
        return Math.abs(getTimeLeft());
    }

    @NotNull
    @Override
    default String getTimeDifferenceAsConsoleString() {
        return TimeConverter.CONSOLE.convert(getTimeLeft()).toString();
    }

    @NotNull
    @Override
    default String getTimeDifferenceAsString() {
        return TimeConverter.CONSOLE.convert(getTimeLeft()).toString();
    }

}
