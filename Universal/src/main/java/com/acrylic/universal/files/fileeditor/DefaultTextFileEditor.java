package com.acrylic.universal.files.fileeditor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class DefaultTextFileEditor implements StandardTextFileEditor {

    private final List<String> contents;

    public DefaultTextFileEditor(@NotNull File file) {
        this.contents = new LinkedList<>();
        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()), StandardCharsets.UTF_8));
            in.lines().forEach(contents::add);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DefaultTextFileEditor(@NotNull List<String> contents) {
        this.contents = contents;
    }

    @NotNull
    @Override
    public List<String> getRawContents() {
        return this.contents;
    }

    @Override
    public void writeNewLine(@NotNull String str) {
        this.contents.add(str);
    }

    @Override
    public void writeLine(int i, @NotNull String str) {
        this.contents.set(getSafeIndex(i), str);
    }

    @Nullable
    @Override
    public String readLine(int i) {
        return contents.get(getSafeIndex(i));
    }

    public int getSafeIndex(int i) {
        return Math.max(0, Math.min(contents.size() - 1, i));
    }

    @Override
    public void clearFileEditor() {
        contents.clear();
    }
}
