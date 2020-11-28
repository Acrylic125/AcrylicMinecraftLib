package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

public class StringValue extends AbstractConfigValue<String> {

    private String obj;

    public StringValue(@NotNull String val) {
        super(val);
    }

    @Override
    public void setup(String reformattedString) {
        this.obj = reformattedString;
    }

    @Override
    public String get() {
        return obj;
    }
}
