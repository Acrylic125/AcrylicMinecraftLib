package com.acrylic.universal.versionstore.exceptions;

public class WrongVersionException extends RuntimeException {

    private int requiredVersion;

    public WrongVersionException(int requiredVersion) {
        this.requiredVersion = requiredVersion;
    }

    @Override
    public String toString() {
        return "WrongVersionException: This field/method/class required version 1." + requiredVersion + "+.";
    }
}
