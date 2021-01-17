package com.acrylic.universal.files;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JSONFile extends AbstractJacksonFile {

    public JSONFile(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(path, new ObjectMapper(new JsonFactory()), plugin);
    }

    public JSONFile(@NotNull String path) {
        super(path, new ObjectMapper(new JsonFactory()));
    }
}
