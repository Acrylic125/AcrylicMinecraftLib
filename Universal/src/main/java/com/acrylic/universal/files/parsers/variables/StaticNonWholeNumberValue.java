package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public class StaticNonWholeNumberValue extends AbstractStaticNumberValue<Double> {

    private double val;

    public StaticNonWholeNumberValue(@NotNull String val) {
        super(val);
    }

    @Override
    public Double get() {
        return val;
    }

    @Override
    public void setup(String reformattedString) {
        val = parse(reformattedString);
    }

}
