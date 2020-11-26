package com.acrylic.universal.files;

import com.acrylic.universal.files.fileeditor.DefaultFileEditor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public abstract class AbstractJacksonFile extends AbstractFile {

    private final ObjectMapper mapper;

    public AbstractJacksonFile(@NotNull String path, @NotNull ObjectMapper mapper) {
        super(path);
        this.mapper = mapper;
        mapper.findAndRegisterModules();
        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
    }

    public void addFileEditor() {
        setFileEditor(new DefaultFileEditor(getFile(), mapper));
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public void saveFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(getFile(), getFileEditor().getContents());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
