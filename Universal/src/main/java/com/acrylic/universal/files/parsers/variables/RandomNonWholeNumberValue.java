package com.acrylic.universal.files.parsers.variables;

import com.acrylic.math.ProbabilityKt;
import org.jetbrains.annotations.NotNull;

public class RandomNonWholeNumberValue extends NonWholeNumber implements RandomNumberValue {

    private double[] values;

    public RandomNonWholeNumberValue(@NotNull String val, short dp) {
        super(val, dp);
    }

    @Override
    public double getRawValue() {
        return values[ProbabilityKt.getRandom(0, values.length - 1)];
    }
    
    @Override
    public void setup(String reformattedString) {
        Number[] numbers = generateNumbers(reformattedString);
        values = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            values[i] = numbers[i].doubleValue();
    }

}
