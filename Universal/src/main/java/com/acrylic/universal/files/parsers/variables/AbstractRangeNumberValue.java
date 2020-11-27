package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class AbstractRangeNumberValue<T extends Number> extends NumericConfigValue<T> {

    public static final Pattern SPLITTER = Pattern.compile("-");

    public AbstractRangeNumberValue(@NotNull String val) {
        super(val);
    }

    protected Number[] generateNumbers(@NotNull String reformattedString) {
        try {
            String[] split = SPLITTER.split(reformattedString);
            if (split.length != 2)
                throw new VariableParserException("The only way to specify a range is by specifying a '-' in between 2 numbers (a-b). For example, 4-8.");
            return new Number[] {
                    Double.parseDouble(split[0]),
                    Double.parseDouble(split[1])
            };
        } catch (NumberFormatException ex) {
            throw new VariableParserException("The value range must both be a valid number.");
        }
    }

}
