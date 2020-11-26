package com.acrylic.universal.files.parsers.variables;

import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

/**
 * RandomSelectedValue usage:
 * v: a|b|c|d|e....
 *
 * Example:
 * var: 4|2|5|22|103|42004020
 */
public class RandomSelectedValue extends NumericConfigVariable {

    public static final String KEY_IDENTIFIER = "|";

    private final double[] values;

    public RandomSelectedValue(@NotNull String str) {
        super(str);
        try {
            String[] split = getReformattedString().split("\\|");
            values = new double[split.length];
            for (int i = 0; i < split.length; i++)
                values[i] = Double.parseDouble(split[i]);
        } catch (NumberFormatException ex) {
            throw new VariableParserException();
        }
    }

    @Override
    public Double get() {
        return values[ProbabilityKt.getRandom(0, values.length - 1)];
    }
}
