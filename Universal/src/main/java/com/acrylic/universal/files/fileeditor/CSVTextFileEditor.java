package com.acrylic.universal.files.fileeditor;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CSVTextFileEditor extends DefaultTextFileEditor {

    public static Pattern COMMA_SEPARATOR = Pattern.compile(",");

    private final Pattern pattern;

    public CSVTextFileEditor(@NotNull Pattern sep, @NotNull File file) {
        super(file);
        this.pattern = sep;
    }

    public CSVTextFileEditor(@NotNull Pattern sep, @NotNull List<String> contents) {
        super(contents);
        this.pattern = sep;
    }

    public CSVTextFileEditor(@NotNull String sep, @NotNull File file) {
        this(Pattern.compile(sep), file);
    }

    public CSVTextFileEditor(@NotNull String sep, @NotNull List<String> contents) {
        this(Pattern.compile(sep), contents);
    }

    public void writeNewLine(@NotNull String[] str) {
        getRawContents().add(convertToString(str));
    }

    public void writeNewLine(@NotNull Object... str) {
        getRawContents().add(convertToString(str));
    }

    public void writeLine(int i, @NotNull Object... str) {
        getRawContents().set(getSafeIndex(i), convertToString(str));
    }

    public String convertToString(@NotNull String[] str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0, m = str.length;
        String p = pattern.pattern();
        for (Object s : str) {
            i++;
            stringBuilder.append(s);
            if (i < m)
                stringBuilder.append(p);
        }
        return stringBuilder.toString();
    }

    public String convertToString(@NotNull Object... str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0, m = str.length;
        String p = pattern.pattern();
        for (Object s : str) {
            i++;
            stringBuilder.append(s);
            if (i < m)
                stringBuilder.append(p);
        }
        return stringBuilder.toString();
    }

    public String[] toStringArray(@NotNull String str) {
        return pattern.split(str);
    }

    @NotNull
    public Pattern getSeparator() {
        return pattern;
    }

    @NotNull
    public String[] readLineContents(int i) {
        return pattern.split(readLine(i));
    }

}
