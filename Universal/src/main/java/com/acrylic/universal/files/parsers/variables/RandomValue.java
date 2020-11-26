package com.acrylic.universal.files.parsers.variables;

import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public class RandomValue extends NumericConfigVariable {

    public static final String KEY_IDENTIFIER = "-";

    private final double min;
    private final double max;

    public RandomValue(@NotNull String str) {
        super(str);
        try {
            String[] split = getReformattedString().split("-");
            if (split.length < 2)
                throw new VariableParserException("Random values only accepts 2 values i.e. \"3-5\"");
            min = Double.parseDouble(split[0]);
            max = Double.parseDouble(split[1]);
        } catch (NumberFormatException ex) {
            throw new VariableParserException();
        }
    }

    @Override
    public Double get() {
        return ProbabilityKt.getRandom(min, max);
    }
}
