package com.acrylic.universal.animations;

import com.acrylic.universal.interfaces.Timed;

public abstract class TimedAnimation
        extends NonContinuousAnimation
        implements Timed {

    private long time = 0;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
