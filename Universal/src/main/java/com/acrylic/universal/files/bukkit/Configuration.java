package com.acrylic.universal.files.bukkit;

import com.acrylic.universal.Universal;
import com.acrylic.universal.files.YMLFile;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Configuration extends YMLFile implements BukkitConfiguration {

    private final JavaPlugin plugin;

    public Configuration(@NotNull String fileName) {
        super(fileName);
        this.plugin = null;
    }

    public Configuration(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(BukkitConfiguration.getPluginConfigurationPath((plugin == null) ? Universal.getPlugin() : plugin, path));
        this.plugin = (plugin == null) ? Universal.getPlugin() : plugin;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void load() {
        addFileEditor();
    }

    @Override
    public void loadFromResources(@NotNull JavaPlugin plugin) {
       loadFromResources(plugin, getFile().getName());
    }

    @Override
    public void loadFromResources(@NotNull JavaPlugin plugin, @NotNull String... resourcePath) {
        if (isJustCreated()) {
            File file = getFile();
            StringBuilder pathBuilder = new StringBuilder();
            for (String s : resourcePath)
                pathBuilder.append(s);
            copy(plugin.getResource(pathBuilder.toString()), file);
        }
        load();
    }

    /** Helper method **/
    private byte[] getInputBuffer(@NotNull InputStream inputStream) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        return buffer;
    }

    /** Helper method **/
    private void copy(InputStream streamFrom, File copyTo) {
        if (streamFrom == null || copyTo == null)
            return;
        try {
            FileUtils.copyInputStreamToFile(streamFrom, copyTo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }





}
