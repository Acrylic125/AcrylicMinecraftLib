package com.acrylic.universal.files;

import com.acrylic.universal.files.AbstractFile;
import com.acrylic.universal.files.DATFile;
import com.acrylic.universal.files.fileeditor.CSVTextFileEditor;
import com.acrylic.universal.files.fileeditor.DefaultTextFileEditor;
import com.acrylic.universal.files.fileeditor.StandardTextFileEditor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFile extends AbstractFile {

    private CSVTextFileEditor fileEditor;

    public CSVFile(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(path, plugin);
    }

    public CSVFile(@NotNull String path) {
        super(path);
    }

    public void setFileEditor(@NotNull CSVTextFileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    public void load() {
        addFileLoader();
    }

    public void addFileLoader() {
        setFileEditor(new CSVTextFileEditor(CSVTextFileEditor.COMMA_SEPARATOR, getFile()));
    }

    @NotNull
    @Override
    public CSVTextFileEditor getFileEditor() {
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
