package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.variables.ConfigVariable;
import com.acrylic.universal.files.parsers.variables.VariableParser;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVariableParser<T>
        implements Parser<Map<String, Object>, T>, VariableParser {

    public static final String KEY_VARIABLES = "variables";

    private final Map<String, ConfigVariable<?>> variableMap = new HashMap<>();

    public AbstractVariableParser() { }

    public AbstractVariableParser(FileEditor fileEditor) {
        fileEditor.getFileEditor(KEY_VARIABLES).getContents().forEach((var, val) -> {
            variableMap.put(ConfigIdentifiers.VARIABLE_IDENTIFIER_PATTERN.toString() + var, ConfigVariable.getInstance(val.toString()));
        });
    }

    @NotNull
    public Map<String, ConfigVariable<?>> getVariableMap() {
        return variableMap;
    }

    public Object getObject(@NotNull String var) {
        return getVariableMap().get(var).get();
    }


}
