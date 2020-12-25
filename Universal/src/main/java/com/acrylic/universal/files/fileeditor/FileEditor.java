package com.acrylic.universal.files.fileeditor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.util.Map;

public interface FileEditor extends BukkitFileEditor {

    @NotNull
    Map<String, Object> getContents();

    @NotNull
    FileEditor getFileEditor(String s);

    @Nullable
    FileEditor getParent();

    @NotNull
    FileEditor getInstance();

    void saveToBufferedWriter(BufferedWriter bufferedWriter);

    default void remove(@NotNull String var) {
        getContents().remove(var);
    }

}
