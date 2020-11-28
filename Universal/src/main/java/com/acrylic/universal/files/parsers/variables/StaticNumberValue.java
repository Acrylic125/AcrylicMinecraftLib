package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public interface StaticNumberValue {

    default double parse(@NotNull String reformattedString) {
        try {
            return Double.parseDouble(reformattedString);
        } catch (NumberFormatException ex) {
            throw new VariableParserException("The value must be a valid number.", reformattedString);
        }
    }

}
