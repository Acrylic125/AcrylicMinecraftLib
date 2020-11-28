package com.acrylic.universal.files.parsers.exceptions;

import org.jetbrains.annotations.NotNull;

public class VariableParserMismatchException extends RuntimeException {

    public VariableParserMismatchException() {
    }

    public VariableParserMismatchException(@NotNull String of, @NotNull String requiredType) {
        super(of + " meant to be of type " + requiredType);
    }

    @Override
    public String toString() {
        String message = getMessage();
        return "VariableParserMismatchException: Variable mismatch parsed exception. The variable you are trying to use does not match the required type of this context. For example, (int) x as (boolean) z. " + ((message == null) ? "" : message);
    }
}
