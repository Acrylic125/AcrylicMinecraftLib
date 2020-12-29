package com.acrylic.universal.files.parsers.variables;

import math.ProbabilityKt;
import org.jetbrains.annotations.NotNull;

public class RangeWholeNumberValue extends WholeNumberValue implements RangeNumberValue {

    private long r1;
    private long r2;

    public RangeWholeNumberValue(@NotNull String val) {
        super(val);
    }

    @Override
    public Long get() {
        return ProbabilityKt.getRandom(r1, r2);
    }
    
    @Override
    public void setup(String reformattedString) {
        Number[] numbers = generateNumbers(reformattedString);
        r1 = numbers[0].longValue();
        r2 = numbers[1].longValue();
    }

}
