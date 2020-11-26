package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.variables.VariableCollective;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVariableParser<T>
        implements Parser<Map<String, Object>, T> {

    public static final String KEY_VARIABLES = "variables";

    private final Map<String, VariableCollective> variableMap = new HashMap<>();

    public AbstractVariableParser(FileEditor fileEditor) {
        fileEditor.getFileEditor(KEY_VARIABLES).getContents().forEach((var, val) -> {
            variableMap.put(var, new VariableCollective(val.toString()));
        });
    }

    public Map<String, VariableCollective> getVariableMap() {
        return variableMap;
    }
}
