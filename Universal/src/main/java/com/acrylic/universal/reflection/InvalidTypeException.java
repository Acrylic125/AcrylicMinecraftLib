package com.acrylic.universal.reflection;

public class InvalidTypeException extends Exception {

    @Override
    public String toString() {
        return "InvalidTypeException (ReflectionUtils): The type retrieved is not the same requested type!";
    }
}
