package com.acrylic.universal.interfaces;

public interface Timer extends Timed {

    boolean isTimerActive();

    Timer setTimerActive(boolean isTimerActive);

}
