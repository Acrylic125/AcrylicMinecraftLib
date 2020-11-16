package com.acrylic.universal.gui.exceptions;

public class NoTemplateDefined extends RuntimeException {

    @Override
    public String toString() {
        return "NoTemplateDefined: No template defined! Please define a template before using this method.";
    }
}
