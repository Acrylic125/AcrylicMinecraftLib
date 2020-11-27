package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import com.acrylic.weights.WeightObject;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public abstract class AbstractConfigValue<T> implements WeightObject {

    public static final Pattern PERCENTAGE = Pattern.compile("%");

    private final double weight;

    public AbstractConfigValue(@NotNull String val) {
        String[] str = PERCENTAGE.split(val);
        try {
            this.weight = (str.length > 1) ? Double.parseDouble(str[0]) : 1;
        } catch (NumberFormatException ex) {
            throw new VariableParserException("a%b where b is the returning value, a must be a valid number.");
        }
        if (weight <= 0)
            throw new VariableParserException("a%b where b is the returning value, a must be a positive non zero number.");
        setup(str[str.length - 1]);
    }

    public abstract void setup(String reformattedString);

    public abstract T get();

    @Override
    public double getWeight() {
        return weight;
    }
}
