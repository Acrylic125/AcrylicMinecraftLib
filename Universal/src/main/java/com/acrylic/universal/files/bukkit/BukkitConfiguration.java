package com.acrylic.universal.files.bukkit;

import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface BukkitConfiguration {

    JavaPlugin getPlugin();

    void load();

    void loadFromResources(@NotNull JavaPlugin plugin);

    void loadFromResources(@NotNull JavaPlugin plugin, @NotNull String resourcePath);

    void loadFromResources(@NotNull JavaPlugin plugin, @NotNull String... resourcePath);

    static String getPluginConfigurationPath(@NotNull JavaPlugin plugin, @NotNull String fileName) {
        return plugin.getDataFolder() + File.separator + fileName;
    }

    static String getPluginConfigurationPath(@NotNull String fileName) {
        return getPluginConfigurationPath(Universal.getPlugin(), fileName);
    }

}
