package com.acrylic.universal.files.fileeditor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.util.Map;
import java.util.function.Consumer;

public interface FileEditor extends BukkitFileEditor {

    @NotNull
    Map<String, Object> getContents();

    @NotNull
    FileEditor getFileEditor(String s);

    @NotNull
    default FileEditor getFileEditor(@NotNull String... path) {
        FileEditor fileEditor = this;
        for (String s : path)
            fileEditor = fileEditor.getFileEditor(s);
        return fileEditor;
    }

    @Nullable
    FileEditor getParent();

    @NotNull
    FileEditor getInstance();

    void saveToBufferedWriter(BufferedWriter bufferedWriter);

    default void remove(@NotNull String var) {
        getContents().remove(var);
    }

    default void safeFileAccess(@NotNull Consumer<FileEditor> action) {
        safeFileAccess(this, action);
    }

    static void safeFileAccess(@NotNull FileEditor fileEditor, @NotNull Consumer<FileEditor> action) {
        try {
            action.accept(fileEditor);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong when trying to load in the in the file editor, " + fileEditor);
        }
    }

}
