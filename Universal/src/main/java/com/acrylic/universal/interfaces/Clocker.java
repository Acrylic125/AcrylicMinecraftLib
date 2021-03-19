package com.acrylic.universal.interfaces;

public interface Clocker extends Clocked {

    boolean isTimerActive();

    void setTimerActive(boolean isTimerActive);

}
