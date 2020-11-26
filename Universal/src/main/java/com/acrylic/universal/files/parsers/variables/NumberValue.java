package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public class NumberValue extends NumericConfigVariable {

    private final double value;

    public NumberValue(@NotNull String str) {
        super(str);
        try {
            value = Double.parseDouble(getReformattedString());
        } catch (NumberFormatException ex) {
            throw new VariableParserException();
        }
    }

    @Override
    public Double get() {
        return value;
    }
}
