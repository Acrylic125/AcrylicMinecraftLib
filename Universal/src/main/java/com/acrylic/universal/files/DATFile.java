package com.acrylic.universal.files;

import com.acrylic.universal.files.fileeditor.DefaultTextFileEditor;
import com.acrylic.universal.files.fileeditor.StandardTextFileEditor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DATFile extends AbstractFile {

    private StandardTextFileEditor fileEditor;

    public DATFile(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(path, plugin);
    }

    public DATFile(@NotNull String path) {
        super(path);
    }

    public void setFileEditor(@NotNull StandardTextFileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    public void load() {
        addFileLoader();
    }

    public void addFileLoader() {
        setFileEditor(new DefaultTextFileEditor(getFile()));
    }

    @NotNull
    @Override
    public StandardTextFileEditor getFileEditor() {
        return fileEditor;
    }

    @Override
    public void saveFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFile().getPath()));
            for (String rawContent : fileEditor.getRawContents()) {
                bw.write(rawContent);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
