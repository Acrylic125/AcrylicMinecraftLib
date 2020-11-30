package com.acrylic.universal.regions.exceptions;

public class InvalidLocationException extends RuntimeException {

    public InvalidLocationException() {
    }

    public InvalidLocationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String exception = getMessage();
        return "InvalidLocationException: " + ((exception == null) ? "" : exception);
    }
}
