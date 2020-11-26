package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public abstract class BaseConfigVariable<T> implements ConfigVariable<T> {

    private final String reformattedString;
    private final double weight;

    public BaseConfigVariable(@NotNull String str) {
        try {
            String[] split = str.split("%");
            if (split.length > 1) {
                weight = Double.parseDouble(split[0]);
                reformattedString = split[1];
            } else {
                weight = 1;
                reformattedString = str;
            }
        } catch (NumberFormatException ex) {
            throw new VariableParserException();
        }
    }

    public String getReformattedString() {
        return reformattedString;
    }

    @Override
    public double getWeight() {
        return weight;
    }


}
