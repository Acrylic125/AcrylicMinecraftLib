package com.acrylic.universal.files.bukkit;

import com.acrylic.universal.Universal;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface BukkitConfiguration {

    @Nullable
    JavaPlugin getPlugin();

    boolean isJustCreated();

    @NotNull
    File getFile();

    void load();

    default void loadFromResources(@NotNull JavaPlugin plugin) {
        loadFromResources(plugin, getFile().getName());
    }

    default void loadFromResources(@NotNull JavaPlugin plugin, @NotNull String resourcePath) {
        if (isJustCreated()) {
            File file = getFile();
            copy(plugin.getResource(resourcePath), file);
        }
        load();
    }

    default void loadFromResources(@NotNull JavaPlugin plugin, @NotNull String... resourcePath) {
        if (isJustCreated()) {
            File file = getFile();
            StringBuilder pathBuilder = new StringBuilder();
            int i = 0, size = resourcePath.length;
            for (String s : resourcePath) {
                i++;
                if (i < size)
                    pathBuilder.append("/");
                pathBuilder.append(s);
            }
            copy(plugin.getResource(pathBuilder.toString()), file);
        }
        load();
    }

    static String getPluginConfigurationPath(@NotNull JavaPlugin plugin, @NotNull String fileName) {
        return plugin.getDataFolder() + File.separator + fileName;
    }

    static String getPluginConfigurationPath(@NotNull String fileName) {
        return getPluginConfigurationPath(Universal.getPlugin(), fileName);
    }

    /** Helper method **/
    default void copy(InputStream streamFrom, File copyTo) {
        if (streamFrom == null || copyTo == null)
            return;
        try {
            FileUtils.copyInputStreamToFile(streamFrom, copyTo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
