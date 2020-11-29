package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.files.parsers.variables.VariableStore;
import com.acrylic.universal.text.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class ParserMap<T> {

    private final Map<String, Object> parseFrom;
    private final VariableStore variables;
    private final String[] replaceFrom;
    private final String[] replaceTo;

    @SuppressWarnings("unchecked")
    public ParserMap(AbstractVariableParser<T> parser, Object map) {
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

    ParserMap(Map<String, Object> parseFrom, VariableStore variables, String[] replaceFrom, String[] replaceTo) {
        this.parseFrom = parseFrom;
        this.variables = variables;
        this.replaceFrom = replaceFrom;
        this.replaceTo = replaceTo;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public Map<String, Object> getMap(@NotNull String var) {
        Object map = parseFrom.get(var);
        return (map instanceof Map<?, ?>) ? (Map<String, Object>) map : null;
    }

    @Nullable
    public ParserMap<T> getParserMap(@NotNull String var) {
        Map<String, Object> map = getMap(var);
        if (map == null)
            return null;
        return new ParserMap<>(map, variables, replaceFrom, replaceTo);
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

    public byte parseByte(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getByte(str) : Byte.parseByte(str);
    }

    public byte parseByte(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseByte(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public byte parseByte(@Nullable Object obj, byte defaultValue) {
        return (obj == null) ? defaultValue : parseByte(obj.toString());
    }

    public byte parseByte(@Nullable Object obj, @NotNull RuntimeException exception, byte defaultValue) {
        return (obj == null) ? defaultValue : parseByte(obj.toString(), exception);
    }

    public byte getByte(@Nullable String key, byte defaultValue) {
        return parseByte(parseFrom.get(key), defaultValue);
    }

    public byte getByte(@Nullable String key, @NotNull RuntimeException exception, byte defaultValue) {
        return parseByte(parseFrom.get(key), exception, defaultValue);
    }

    public short parseShort(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getShort(str) : Short.parseShort(str);
    }

    public short parseShort(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseShort(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public short parseShort(@Nullable Object obj, short defaultValue) {
        return (obj == null) ? defaultValue : parseShort(obj.toString());
    }

    public short parseShort(@Nullable Object obj, @NotNull RuntimeException exception, short defaultValue) {
        return (obj == null) ? defaultValue : parseShort(obj.toString(), exception);
    }

    public short getShort(@Nullable String key, short defaultValue) {
        return parseShort(parseFrom.get(key), defaultValue);
    }

    public short getShort(@Nullable String key, @NotNull RuntimeException exception, short defaultValue) {
        return parseShort(parseFrom.get(key), exception, defaultValue);
    }

    public int parseInteger(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getInteger(str) : Integer.parseInt(str);
    }

    public int parseInteger(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseInteger(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public int parseInteger(@Nullable Object obj, int defaultValue) {
        return (obj == null) ? defaultValue : parseInteger(obj.toString());
    }

    public int parseInteger(@Nullable Object obj, @NotNull RuntimeException exception, int defaultValue) {
        return (obj == null) ? defaultValue : parseInteger(obj.toString(), exception);
    }

    public int getInteger(@Nullable String key, int defaultValue) {
        return parseInteger(parseFrom.get(key), defaultValue);
    }

    public int getInteger(@Nullable String key, @NotNull RuntimeException exception, int defaultValue) {
        return parseInteger(parseFrom.get(key), exception, defaultValue);
    }

    public long parseLong(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getWholeNumber(str) : Long.parseLong(str);
    }

    public long parseLong(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseLong(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public long parseLong(@Nullable Object obj, long defaultValue) {
        return (obj == null) ? defaultValue : parseLong(obj.toString());
    }

    public long parseLong(@Nullable Object obj, @NotNull RuntimeException exception, long defaultValue) {
        return (obj == null) ? defaultValue : parseLong(obj.toString(), exception);
    }

    public long getLong(@Nullable String key, long defaultValue) {
        return parseLong(parseFrom.get(key), defaultValue);
    }

    public long getLong(@Nullable String key, @NotNull RuntimeException exception, long defaultValue) {
        return parseLong(parseFrom.get(key), exception, defaultValue);
    }

    public float parseFloat(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getFloat(str) : Float.parseFloat(str);
    }

    private float parseFloat(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseFloat(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public float parseFloat(@Nullable Object obj, float defaultValue) {
        return (obj == null) ? defaultValue : parseFloat(obj.toString());
    }

    public float parseFloat(@Nullable Object obj, @NotNull RuntimeException exception, float defaultValue) {
        return (obj == null) ? defaultValue : parseFloat(obj.toString(), exception);
    }

    public float getFloat(@Nullable String key, float defaultValue) {
        return parseFloat(parseFrom.get(key), defaultValue);
    }

    public float getFloat(@Nullable String key, @NotNull RuntimeException exception, float defaultValue) {
        return parseFloat(parseFrom.get(key), exception, defaultValue);
    }

    public double parseDouble(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getNonWholeNumber(str) : Double.parseDouble(str);
    }

    public double parseDouble(@NotNull String str, @NotNull RuntimeException exception) {
        try {
            return parseDouble(str);
        } catch (NumberFormatException ex) {
            throw exception;
        }
    }

    public double parseDouble(@Nullable Object obj, double defaultValue) {
        return (obj == null) ? defaultValue : parseDouble(obj.toString());
    }

    public double parseDouble(@Nullable Object obj, @NotNull RuntimeException exception, double defaultValue) {
        return (obj == null) ? defaultValue : parseDouble(obj.toString(), exception);
    }

    public double getDouble(@Nullable String key, double defaultValue) {
        return parseDouble(parseFrom.get(key), defaultValue);
    }

    public double getDouble(@Nullable String key, @NotNull RuntimeException exception, double defaultValue) {
        return parseDouble(parseFrom.get(key), exception, defaultValue);
    }

    public String parseString(@NotNull String str) {
        return ChatUtils.get(scanStringForVariables((shouldTryVariableQuery(str))
                ? variables.getString(str) : str));
    }

    public String parseString(@Nullable Object obj, @Nullable String defaultValue) {
        return (obj == null) ? defaultValue : parseString(obj.toString());
    }

    public String getString(@Nullable String key, String defaultValue) {
        return parseString(parseFrom.get(key), defaultValue);
    }

    public boolean parseBoolean(@NotNull String str) {
        return (shouldTryVariableQuery(str))
                ? variables.getBoolean(str) : Boolean.parseBoolean(str);
    }

    public boolean parseBoolean(@Nullable Object obj, boolean defaultValue) {
        return (obj == null) ? defaultValue : parseBoolean(obj.toString());
    }

    public boolean getBoolean(@Nullable String key, boolean defaultValue) {
        return parseBoolean(parseFrom.get(key), defaultValue);
    }

}
