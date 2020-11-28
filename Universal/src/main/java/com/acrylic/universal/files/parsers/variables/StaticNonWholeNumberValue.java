package com.acrylic.universal.files.parsers.variables;

import org.jetbrains.annotations.NotNull;

public class StaticNonWholeNumberValue extends NonWholeNumber implements StaticNumberValue {

    private double val;

    public StaticNonWholeNumberValue(@NotNull String val, short dp) {
        super(val, dp);
    }

    @Override
    public double getRawValue() {
        return val;
    }

    @Override
    public void setup(String reformattedString) {
        val = parse(reformattedString);
    }

}
