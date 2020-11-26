package com.acrylic.universal.files.parsers.variables;

import com.acrylic.weights.Weigher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VariableCollective {

    public static final String KEY_NUM_IDENTIFIER = "\\(N\\)";

    private final Weigher<ConfigVariable<Double>> numericVariables = new Weigher<>();

    public VariableCollective(@Nullable String variableStr) {
        if (variableStr == null)
            return;
        if (variableStr.contains(KEY_NUM_IDENTIFIER))
            generateNumericVariables(variableStr);
    }

    private void generateNumericVariables(@NotNull String variableStr) {
        variableStr = variableStr.replaceFirst(KEY_NUM_IDENTIFIER, "");
        String[] split = variableStr.split(",");
        for (String var : split) {
            if (variableStr.contains(RandomSelectedValue.KEY_IDENTIFIER))
                numericVariables.add(new RandomSelectedValue(var));
            else if (variableStr.contains(RandomValue.KEY_IDENTIFIER))
                numericVariables.add(new RandomValue(var));
            else
                numericVariables.add(new NumberValue(var));
        }
    }

    public Double getNumberValue() {
        ConfigVariable<Double> var = numericVariables.get();
        if (var == null)
            return null;
        return var.get();
    }

}
