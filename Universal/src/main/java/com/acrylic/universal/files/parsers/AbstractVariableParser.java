package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.variables.ConfigVariable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVariableParser<T>
        implements Parser<Map<String, Object>, T> {

    public static final String KEY_VARIABLES = "variables";

    private final Map<String, ConfigVariable<?>> variableMap = new HashMap<>();

    public AbstractVariableParser() { }

    public AbstractVariableParser(FileEditor fileEditor) {
        fileEditor.getFileEditor(KEY_VARIABLES).getContents().forEach((var, val) -> {
            variableMap.put(var, ConfigVariable.getInstance(var));
        });
    }

    public Map<String, ConfigVariable<?>> getVariableMap() {
        return variableMap;
    }
}
