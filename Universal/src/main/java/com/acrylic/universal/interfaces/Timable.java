package com.acrylic.universal.interfaces;

import org.jetbrains.annotations.NotNull;

public interface Timable {

    void setTime(long time);

    long getTime();

    default void addTime(long time) {
        setTime(getTime() + time);
    }

    default void removeTime(long time) {
        setTime(getTime() - time);
    }

    default void addTimeToNow(long time) {
        setTime(System.currentTimeMillis() + time);
    }

    default void removeTimeToNow(long time) {
        setTime(System.currentTimeMillis() - time);
    }

    long getTimeDifference();

    @NotNull
    String getTimeDifferenceAsConsoleString();

    @NotNull
    String getTimeDifferenceAsString();

}
