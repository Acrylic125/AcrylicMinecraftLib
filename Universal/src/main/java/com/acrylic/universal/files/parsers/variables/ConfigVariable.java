package com.acrylic.universal.files.parsers.variables;

import com.acrylic.universal.files.parsers.ConfigIdentifiers;
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
            str = matcher.replaceFirst(str);
        String[] split = SPLIT_BETWEEN_VALUES.split(str);
        for (String v : split)
            weigher.add(supplierLogic.getConfigValue(v));
    }

    public static ConfigVariable<?> getInstance(@NotNull String str) {
        final Matcher wholeNumberMatcher = ConfigIdentifiers.WHOLE_NUMBER_PATTERN.matcher(str);
        if (wholeNumberMatcher.find())
            return new ConfigVariable<>(str, wholeNumberMatcher, WHOLE_NUMBER_SUPPLIER);
        final Matcher nonWholeNumberMatcher = ConfigIdentifiers.NON_WHOLE_NUMBER_PATTERN.matcher(str);
        if (nonWholeNumberMatcher.find())
            return new ConfigVariable<>(str, wholeNumberMatcher, NON_WHOLE_NUMBER_SUPPLIER);
        return new ConfigVariable<>(str, ObjectValue::new);
    }

    /**
     * Helpers.
     */
    private static final ConfigSupplierLogic<Long> WHOLE_NUMBER_SUPPLIER =
         toParse -> {
            if (AbstractRandomNumberValue.SPLITTER.matcher(toParse).find())
                return new RandomWholeNumberValue(toParse);
            else if (AbstractRangeNumberValue.SPLITTER.matcher(toParse).find())
                return new RandomWholeNumberValue(toParse);
            else
                return new StaticWholeNumberValue(toParse);
    };

    private static final ConfigSupplierLogic<Double> NON_WHOLE_NUMBER_SUPPLIER = toParse -> {
        if (AbstractRandomNumberValue.SPLITTER.matcher(toParse).find())
            return new RandomNonWholeNumberValue(toParse);
        else if (AbstractRangeNumberValue.SPLITTER.matcher(toParse).find())
            return new RandomNonWholeNumberValue(toParse);
        else
            return new StaticNonWholeNumberValue(toParse);
    };


}
