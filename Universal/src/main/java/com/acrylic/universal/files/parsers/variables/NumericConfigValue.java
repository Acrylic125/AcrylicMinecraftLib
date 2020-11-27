package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class NumericConfigValue<T extends Number> extends AbstractConfigValue<T> {

    public NumericConfigValue(@NotNull String val) {
        super(val);
    }

}
