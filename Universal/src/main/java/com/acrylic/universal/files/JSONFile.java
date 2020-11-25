package com.acrylic.universal.files;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

public class JSONFile extends AbstractJacksonFile {

    public JSONFile(@NotNull String path) {
        super(path, new ObjectMapper(new JsonFactory()));
    }
}
