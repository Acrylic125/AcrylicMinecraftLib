package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

/**
 * NumericVariables represents a numeric value in the config.
 */
public abstract class NumericConfigVariable extends BaseConfigVariable<Double> {

    public NumericConfigVariable(@NotNull String str) {
        super(str);
    }
}
