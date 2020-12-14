package com.acrylic.universal.interfaces;

import com.acrylic.time.Time;
import com.acrylic.universal.utils.TimeConverter;

public interface Timed {

    long getLastTimed();

    void setLastTimed(long time);

    default void clockTime() {
        setLastTimed(System.currentTimeMillis());
    }

    default long getTimeFromLastTimed() {
        return System.currentTimeMillis() - getLastTimed();
    }

    default String getTimeFromLastTimedAsString() {
        return TimeConverter.CONSOLE.convert(getTimeFromLastTimed()).toString();
    }

}
