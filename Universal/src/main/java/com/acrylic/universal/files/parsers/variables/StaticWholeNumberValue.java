package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import org.jetbrains.annotations.NotNull;

public class StaticWholeNumberValue extends AbstractStaticNumberValue<Long> {

    private long val;

    public StaticWholeNumberValue(@NotNull String val) {
        super(val);
    }

    @Override
    public Long get() {
        return val;
    }

    @Override
    public void setup(String reformattedString) {
        val = Math.round(parse(reformattedString));
    }

}
