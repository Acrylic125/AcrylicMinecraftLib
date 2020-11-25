package com.acrylic.universal.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.jetbrains.annotations.NotNull;

public class YMLFile extends AbstractJacksonFile {

    public YMLFile(@NotNull String path) {
        super(path, new ObjectMapper(new YAMLFactory()));
    }
}
