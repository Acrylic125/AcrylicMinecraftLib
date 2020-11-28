package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.files.parsers.variables.VariableStore;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ParserProducer<T> {

    private final Map<String, Object> parseFrom;
    private final VariableStore variables;
    private final String[] replaceFrom;
    private final String[] replaceTo;

    @SuppressWarnings("unchecked")
    public ParserProducer(AbstractVariableParser<T> parser, Object map) {
        if (!(map instanceof Map<?, ?>))
            throw new ParserException("There is no map to parse.");
        this.parseFrom = (Map<String, Object>) map;
        int s = parser.getVariableMap().size();
        this.replaceFrom = new String[s];
        this.replaceTo = new String[s];
        this.variables = new VariableStore(parser, (i, var, val) -> {
            this.replaceFrom[i] = var;
            this.replaceTo[i] = val.toString();
        });
    }

    @NotNull
    public Map<String, Object> getParseFrom() {
        return parseFrom;
    }

    public VariableStore getVariables() {
        return variables;
    }

    public String scanStringForVariables(String from) {
        return StringUtils.replaceEach(from, replaceFrom, replaceTo);
    }

    public boolean shouldTryVariableQuery(@NotNull String str) {
        return ConfigIdentifiers.VARIABLE_IDENTIFIER_PATTERN.matcher(str).find();
    }

    public byte getByte(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getByte(str) : Byte.parseByte(str);
    }

    public byte getByte(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getByte(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public short getShort(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getShort(str) : Short.parseShort(str);
    }

    public short getShort(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getShort(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public int getInteger(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getInteger(str) : Integer.parseInt(str);
    }

    public int getInteger(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getInteger(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public long getLong(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getWholeNumber(str) : Long.parseLong(str);
    }

    public long getLong(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getLong(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public float getFloat(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getFloat(str) : Float.parseFloat(str);
    }

    private float getFloat(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getFloat(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public double getDouble(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getNonWholeNumber(str) : Double.parseDouble(str);
    }

    public double getDouble(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return getDouble(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public String getString(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getString(str) : str;
    }

    public boolean getBoolean(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getBoolean(str) : Boolean.parseBoolean(str);
    }

}
