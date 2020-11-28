package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.math.MathUtils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class NonWholeNumber extends NumericConfigValue<Double> {

    public static final Pattern START_DP_PATTERN = Pattern.compile("<");
    public static final Pattern END_DP_PATTERN = Pattern.compile(">");

    private final short dp;

    public NonWholeNumber(@NotNull String val, short dp) {
        super(val);
        this.dp = dp;
    }

    public short getDecimalPlace() {
        return dp;
    }

    public abstract double getRawValue();

    @Override
    public Double get() {
        return MathUtils.round(getRawValue(), dp);
    }
}
