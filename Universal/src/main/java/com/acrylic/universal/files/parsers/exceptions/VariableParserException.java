package com.acrylic.universal.files.parsers.exceptions;

public class VariableParserException extends RuntimeException {

    public VariableParserException() {
    }

    public VariableParserException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        String message = getMessage();
        return "VariableParserException: Variable parsed exception. Check if you specified a valid variable format. " + ((message == null) ? "" : message);
    }
}
