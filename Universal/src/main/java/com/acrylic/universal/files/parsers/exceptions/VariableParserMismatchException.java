package com.acrylic.universal.files.parsers.exceptions;

public class VariableParserMismatchException extends RuntimeException {

    public VariableParserMismatchException() {
    }

    public VariableParserMismatchException(String to, String type) {
        super(to + " meant to be of type " + type);
    }

    @Override
    public String toString() {
        String message = getMessage();
        return "VariableParserMismatchException: Variable mismatch parsed exception. You are trying to cast a variable as a type that is not of that type. For example, (int) x as (boolean) z. " + ((message == null) ? "" : message);
    }
}
