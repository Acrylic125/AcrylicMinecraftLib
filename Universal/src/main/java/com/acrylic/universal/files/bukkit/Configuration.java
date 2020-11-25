package com.acrylic.universal.files.bukkit;

import com.acrylic.universal.Universal;
import com.acrylic.universal.files.YMLFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class Configuration extends YMLFile implements BukkitConfiguration {

    private final JavaPlugin plugin;

    public Configuration(@NotNull String fileName) {
        super(fileName);
        this.plugin = null;
    }

    public Configuration(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(BukkitConfiguration.getPluginConfigurationPath((plugin == null) ? Universal.getPlugin() : plugin, path));
        this.plugin = plugin;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void saveFile() {
        try {
            getFileEditor().saveToBufferedWriter(new BufferedWriter(new FileWriter(getFile())));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
            OutputStream outputStream = new FileOutputStream(copyTo);
            outputStream.write(getInputBuffer(streamFrom));
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }





}
