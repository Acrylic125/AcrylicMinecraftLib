package com.acrylic.universal.files.fileeditor;

import files.editor.Configurable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;

public interface FileEditor extends Configurable {

    @NotNull
    Map<String, Object> getContents();

    @NotNull
    FileEditor getFileEditor(String s);

    @Nullable
    FileEditor getParent();

    @NotNull
    FileEditor getInstance();

    void saveToBufferedWriter(BufferedWriter bufferedWriter);

}
