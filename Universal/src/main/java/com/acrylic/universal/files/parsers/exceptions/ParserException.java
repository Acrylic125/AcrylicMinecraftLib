package com.acrylic.universal.files.parsers.exceptions;

public class ParserException extends RuntimeException {

    public ParserException() {
    }

    public ParserException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        String message = getMessage();
        return "ParserException: Object parsed exception. Check if you specified the properties correctly. " + ((message == null) ? "" : message);
    }
}
