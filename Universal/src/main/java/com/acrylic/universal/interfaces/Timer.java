package com.acrylic.universal.interfaces;

import com.acrylic.time.Time;
import com.acrylic.time.TimeConverter;

public interface Timer {

    boolean isTimerActive();

    Timer setTimerActive(boolean isTimerActive);

    long getLastClocked();

    void clockTime();

    default long getTimeFromLastClocked() {
        return System.currentTimeMillis() - getLastClocked();
    }

    default String getTimeFromLastClockedAsString() {
        return TimeConverter.GLOBAL.convert(getTimeFromLastClocked(), Time.MILLISECONDS).toString();
    }



}
