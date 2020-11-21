package com.acrylic.universal.animations.exceptions;

public class UnsupportedEntityType extends RuntimeException {

    @Override
    public String toString() {
        return "UnsupportedEntityType: This entity type is not supported.";
    }
}
