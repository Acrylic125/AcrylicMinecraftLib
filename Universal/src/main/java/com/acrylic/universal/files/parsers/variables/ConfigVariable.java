package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.ConfigIdentifiers;
import com.acrylic.universal.files.parsers.exceptions.VariableParserException;
import com.acrylic.weights.Weigher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ConfigVariable<T> {

    public static final Pattern SPLIT_BETWEEN_VALUES = Pattern.compile(",");

    private final Weigher<AbstractConfigValue<T>> weigher = new Weigher<>();

    private ConfigVariable(@NotNull String str, @NotNull ConfigSupplierLogic<T> supplierLogic) {
        this(str, null, supplierLogic);
    }

    private ConfigVariable(@NotNull String str, @Nullable Matcher matcher, @NotNull ConfigSupplierLogic<T> supplierLogic) {
        if (matcher != null)
            str = matcher.replaceFirst("");
        String[] split = SPLIT_BETWEEN_VALUES.split(str);
        for (String v : split)
            weigher.add(supplierLogic.getConfigValue(v));
    }

    @Nullable
    public T get() {
        AbstractConfigValue<T> configValue = weigher.get();
        return (configValue == null) ? null : configValue.get();
    }

    public static ConfigVariable<?> getInstance(@NotNull String str) {
        final Matcher wholeNumberMatcher = ConfigIdentifiers.WHOLE_NUMBER_PATTERN.matcher(str);
        if (wholeNumberMatcher.find())
            return new ConfigVariable<>(str, wholeNumberMatcher, WHOLE_NUMBER_SUPPLIER);
        final Matcher nonWholeNumberMatcher = ConfigIdentifiers.NON_WHOLE_NUMBER_PATTERN.matcher(str);
        if (nonWholeNumberMatcher.find())
            return new ConfigVariable<>(str, nonWholeNumberMatcher, NON_WHOLE_NUMBER_SUPPLIER);
        final Matcher booleanMatcher = ConfigIdentifiers.BOOLEAN_PATTERN.matcher(str);
        if (booleanMatcher.find())
            return new ConfigVariable<>(str, booleanMatcher, BooleanValue::new);
        return new ConfigVariable<>(str, StringValue::new);
    }

    /**
     * Helpers.
     */
    private static final ConfigSupplierLogic<Long> WHOLE_NUMBER_SUPPLIER =
         toParse -> {
            if (RandomNumberValue.SPLITTER.matcher(toParse).find())
                return new RandomWholeNumberValue(toParse);
            else if (RangeNumberValue.SPLITTER.matcher(toParse).find())
                return new RangeWholeNumberValue(toParse);
            else
                return new StaticWholeNumberValue(toParse);
    };

    private static final ConfigSupplierLogic<Double> NON_WHOLE_NUMBER_SUPPLIER = toParse -> {
        int dp = 2;
        if (NonWholeNumber.START_DP_PATTERN.matcher(toParse).find() && NonWholeNumber.END_DP_PATTERN.matcher(toParse).find()) {
            try {
                String[] split = NonWholeNumber.END_DP_PATTERN.split(toParse);
                if (split.length <= 1)
                    throw new VariableParserException("Unbounded specification. You need to close the value with a '>'. For example, (float)<4>.", toParse);
                dp = Integer.parseInt(NonWholeNumber.START_DP_PATTERN.matcher(split[0]).replaceFirst(""));
                if (dp < 0 || dp > 16)
                    throw new VariableParserException("You may only provide a decimal value between 0-16. For example, (float)<4>.", toParse);
                toParse = split[1];
            } catch (NumberFormatException ex) {
                throw new VariableParserException("You may only specify a valid integer value for the amount of decimal place. For example, (float)<4>.", toParse);
            }
        }
        if (RandomNumberValue.SPLITTER.matcher(toParse).find())
            return new RandomNonWholeNumberValue(toParse, (short) dp);
        else if (RangeNumberValue.SPLITTER.matcher(toParse).find())
            return new RangeNonWholeNumberValue(toParse, (short) dp);
        else
            return new StaticNonWholeNumberValue(toParse, (short) dp);
    };


}
