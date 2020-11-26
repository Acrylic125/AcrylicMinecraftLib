package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

public class AbsoluteConfigValue<T extends Number> extends NumericConfigValue<T> {

    private T val;

    public AbsoluteConfigValue(@NotNull String val) {
        super(val);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setup(String reformattedString) {
        val = (T) (Number) Double.parseDouble(reformattedString);
    }

    @Override
    public T get() {
        return val;
    }
}
