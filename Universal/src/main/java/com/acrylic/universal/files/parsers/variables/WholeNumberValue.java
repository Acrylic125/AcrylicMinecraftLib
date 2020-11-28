package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

public abstract class WholeNumberValue extends NumericConfigValue<Long> {

    public WholeNumberValue(@NotNull String val) {
        super(val);
    }

}
