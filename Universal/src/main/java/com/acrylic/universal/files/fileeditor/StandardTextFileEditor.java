package com.acrylic.universal.files.fileeditor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface StandardTextFileEditor extends AbstractFileEditor {

    @NotNull
    List<String> getRawContents();

    void writeNewLine(@NotNull String str);

    void writeLine(int i, @NotNull String str);

    @Nullable
    String readLine(int i);

}
