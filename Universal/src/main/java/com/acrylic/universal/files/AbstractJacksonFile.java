package com.acrylic.universal.files;

import com.acrylic.universal.files.fileeditor.DefaultFileEditor;
import com.acrylic.universal.files.fileeditor.FileEditor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public abstract class AbstractJacksonFile extends AbstractFile {

    private final ObjectMapper mapper;
    private FileEditor fileEditor;

    public AbstractJacksonFile(@NotNull String path, @NotNull ObjectMapper mapper) {
        super(path);
        this.mapper = mapper;
        init();
    }

    public AbstractJacksonFile(@NotNull String path, @NotNull ObjectMapper mapper, @Nullable JavaPlugin plugin) {
        super(path, plugin);
        this.mapper = mapper;
        init();
    }

    private void init() {
        mapper.findAndRegisterModules();
        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS, JsonParser.Feature.ALLOW_YAML_COMMENTS);
    }

    public void addFileEditor() {
        setFileEditor(new DefaultFileEditor(getFile(), mapper));
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setFileEditor(FileEditor fileEditor) {
        this.fileEditor = fileEditor;
    }

    @NotNull
    public FileEditor getFileEditor() {
        return fileEditor;
    }

    @Override
    public void saveFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(getFile(), getFileEditor().getContents());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void load() {
        addFileEditor();
    }

}
