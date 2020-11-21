package com.acrylic.universal.animations;

public abstract class TimedAnimation extends NonContinuousAnimation {

    private long time = 0;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTimeOnCurrent(long time) {
        setTime(System.currentTimeMillis() + time);
    }

    public boolean hasExpired() {
        return System.currentTimeMillis() > time;
    }

}
