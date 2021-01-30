package com.acrylic.universal.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formats a given string based on the formatter.
 *
 * For example,
 * String ID   :   Format With
 * k           | 000
 * m           | 000000
 * b           | 000000000
 */
public class StringFormatter {

    private final Collection<StringFormat> formats;

    public StringFormatter() {
        this(new ArrayList<>());
    }

    public StringFormatter(@NotNull Collection<StringFormat> formats) {
        this.formats = formats;
    }

    @NotNull
    public Collection<StringFormat> getFormats() {
        return formats;
    }

    public void addFormat(@NotNull String replaceWith, @NotNull String... identifiers) {
        formats.add(new StringFormat(identifiers, replaceWith));
    }

    public void removeFormat(@NotNull String replaceWith) {
        for (StringFormat format : formats) {
            if (format.formatWith.equals(replaceWith)) {
                formats.remove(format);
                return;
            }
        }
    }

    public String formatString(@NotNull String string) {
        string = string.toUpperCase(Locale.ENGLISH);
        for (StringFormat format : formats)
            string = format.transform(string);
        return string;
    }

    public static class StringFormat {

        private final Pattern[] identifiers;
        private final String formatWith;

        private StringFormat(@NotNull String[] identifiers, @NotNull String replaceWith) {
            this.identifiers = new Pattern[identifiers.length];
            for (int i = 0; i < identifiers.length; i++)
                this.identifiers[i] = Pattern.compile(identifiers[i].toUpperCase(Locale.ENGLISH));
            this.formatWith = replaceWith;
        }

        private String transform(@NotNull String charSequence) {
            for (Pattern identifier : identifiers) {
                Matcher matcher = identifier.matcher(charSequence);
                charSequence = matcher.replaceAll(this.formatWith);
            }
            return charSequence;
        }
    }

    public static final class NumberStringFormatter extends StringFormatter {

        public NumberStringFormatter() {
            super(new ArrayList<>());
        }

        public NumberStringFormatter(@NotNull Collection<StringFormat> formats) {
            super(formats);
        }

        @Override
        public String formatString(@NotNull String string) {
            return StringUtils.COMMA_PATTERN.matcher(super.formatString(string)).replaceAll("");
        }

        @Override
        public void addFormat(@NotNull String replaceWith, @NotNull String... identifiers) {
            if (!MathUtils.isStringAnInteger(replaceWith))
                throw new IllegalArgumentException(replaceWith + " is not a valid integer format.");
            super.addFormat(replaceWith, identifiers);
        }

        public long formatLong(@NotNull String string) {
            return MathUtils.getLong(formatString(string), 0);
        }

        public double formatDouble(@NotNull String string) {
            return MathUtils.getDouble(formatString(string), 0);
        }

    }

}
