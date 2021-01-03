package com.acrylic.universal.interfaces;

public interface Clocker extends Clocked {

    boolean isTimerActive();

    Clocker setTimerActive(boolean isTimerActive);

}
