package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.AbstractFile;
import com.acrylic.universal.files.DATFile;
import com.acrylic.universal.files.fileeditor.CSVTextFileEditor;
import com.acrylic.universal.files.fileeditor.DefaultTextFileEditor;
import com.acrylic.universal.files.fileeditor.StandardTextFileEditor;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFile extends AbstractFile {

    public CSVFile(@NotNull String path) {
        super(path);
    }

    private CSVTextFileEditor fileEditor;

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
