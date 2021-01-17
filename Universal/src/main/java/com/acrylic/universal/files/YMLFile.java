package com.acrylic.universal.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class YMLFile extends AbstractJacksonFile {

    public YMLFile(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(path, new ObjectMapper(new YAMLFactory()), plugin);
    }

    public YMLFile(@NotNull String path) {
        super(path, new ObjectMapper(new YAMLFactory()));
    }

}
