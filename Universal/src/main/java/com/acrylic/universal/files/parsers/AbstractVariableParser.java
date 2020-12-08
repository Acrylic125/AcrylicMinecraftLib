package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.files.parsers.variables.ConfigVariable;
import com.acrylic.universal.files.parsers.variables.VariableParser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVariableParser<T>
        implements Parser<Map<String, Object>, T>, VariableParser {

    public static final String KEY_VARIABLES = "variables";

    private final Map<String, Object> parseFrom;
    private final Map<String, ConfigVariable<?>> variableMap = new HashMap<>();

    public AbstractVariableParser() {
        this.parseFrom = new HashMap<>();
    }

    public AbstractVariableParser(Map<String, Object> map) {
        if (map == null)
            throw new ParserException("No map to parse! Check if the thing you are trying to parse has the required attributes.");
        Object obj = map.get(KEY_VARIABLES);
        this.parseFrom = map;
        if (obj instanceof Map<?, ?>)
           ((Map<?, ?>) obj).forEach((var, val) -> variableMap.put(ConfigIdentifiers.VARIABLE_IDENTIFIER_PATTERN.toString() + var, ConfigVariable.getInstance(val.toString())));
    }

    public AbstractVariableParser(@NotNull FileEditor fileEditor) {
        this.parseFrom = fileEditor.getContents();
        fileEditor.getFileEditor(KEY_VARIABLES).getContents().forEach((var, val) -> {
            variableMap.put(ConfigIdentifiers.VARIABLE_IDENTIFIER_PATTERN.toString() + var, ConfigVariable.getInstance(val.toString()));
        });
    }

    @Override
    public Map<String, Object> getParserFrom() {
        return parseFrom;
    }

    @NotNull
    public Map<String, ConfigVariable<?>> getVariableMap() {
        return variableMap;
    }

    public Object getObject(@NotNull String var) {
        return getVariableMap().get(var).get();
    }


}
