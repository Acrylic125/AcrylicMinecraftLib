package com.acrylic.universal.files.parsers.variables;

import com.acrylic.math.ProbabilityKt;
import org.jetbrains.annotations.NotNull;

public class RangeNonWholeNumberValue extends AbstractRangeNumberValue<Double> {

    private double r1;
    private double r2;

    public RangeNonWholeNumberValue(@NotNull String val) {
        super(val);
    }

    @Override
    public Double get() {
        return ProbabilityKt.getRandom(r1, r2);
    }

    @Override
    public void setup(String reformattedString) {
        Number[] numbers = generateNumbers(reformattedString);
        r1 = numbers[0].doubleValue();
        r2 = numbers[1].doubleValue();
    }

}
