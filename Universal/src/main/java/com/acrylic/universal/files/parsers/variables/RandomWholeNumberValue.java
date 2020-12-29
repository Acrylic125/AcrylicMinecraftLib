package com.acrylic.universal.files.parsers.variables;

import math.ProbabilityKt;
import org.jetbrains.annotations.NotNull;

public class RandomWholeNumberValue extends WholeNumberValue implements RandomNumberValue {

    private long[] values;

    public RandomWholeNumberValue(@NotNull String val) {
        super(val);
    }

    @Override
    public Long get() {
        return values[ProbabilityKt.getRandom(0, values.length - 1)];
    }

    @Override
    public void setup(String reformattedString) {
        Number[] numbers = generateNumbers(reformattedString);
        values = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            values[i] = Math.round(numbers[i].doubleValue());
    }

}
