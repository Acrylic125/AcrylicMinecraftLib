package com.acrylic.universal.files.bukkit;

import com.acrylic.universal.MCLib;
import com.acrylic.universal.files.YMLFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Configuration
        extends YMLFile
        implements BukkitConfiguration {

    private final JavaPlugin plugin;

    public Configuration(@NotNull String fileName) {
        super(fileName);
        this.plugin = null;
    }

    public Configuration(@NotNull String path, @Nullable JavaPlugin plugin) {
        super(BukkitConfiguration.getPluginConfigurationPath((plugin == null) ? MCLib.getPlugin() : plugin, path));
        this.plugin = (plugin == null) ? MCLib.getPlugin() : plugin;
    }

    @Nullable
    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void load() {
        addFileEditor();
    }

}
