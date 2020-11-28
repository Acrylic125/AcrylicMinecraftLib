package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public interface RandomNumberValue {

    Pattern SPLITTER = Pattern.compile("\\|");

    default Number[] generateNumbers(@NotNull String reformattedString) {
        try {
            String[] split = SPLITTER.split(reformattedString);
            Number[] values = new Number[split.length];
            for (int i = 0; i < split.length; i++) {
                values[i] = Double.parseDouble(split[i]);
            }
            return values;
        } catch (NumberFormatException ex) {
            throw new VariableParserException("The values must all be a valid number.", reformattedString);
        }
    }

}
