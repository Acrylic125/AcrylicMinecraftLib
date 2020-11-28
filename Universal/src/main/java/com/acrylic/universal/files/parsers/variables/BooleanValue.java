package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

public class BooleanValue extends AbstractConfigValue<Boolean> {

    private boolean obj;

    public BooleanValue(@NotNull String val) {
        super(val);
    }

    @Override
    public void setup(String reformattedString) {
        obj = Boolean.parseBoolean(reformattedString);
    }

    @Override
    public Boolean get() {
        return obj;
    }
}
