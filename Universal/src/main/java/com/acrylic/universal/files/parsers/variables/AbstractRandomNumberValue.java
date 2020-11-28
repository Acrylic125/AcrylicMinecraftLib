package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class AbstractRandomNumberValue<T extends Number>
        extends NumericConfigValue<T> {

    public static final Pattern SPLITTER = Pattern.compile("\\|");

    public AbstractRandomNumberValue(@NotNull String val) {
        super(val);
    }

    protected Number[] generateNumbers(@NotNull String reformattedString) {
        try {
            String[] split = SPLITTER.split(reformattedString);
            Number[] values = new Number[split.length];
            for (int i = 0; i < split.length; i++) {
                values[i] = Double.parseDouble(split[i]);
            }
            return values;
        } catch (NumberFormatException ex) {
            throw new VariableParserException("The values must all be a valid number.");
        }
    }

}
