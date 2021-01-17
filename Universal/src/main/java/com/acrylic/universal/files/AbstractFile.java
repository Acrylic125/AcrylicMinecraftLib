package com.acrylic.universal.files;

import com.acrylic.universal.Universal;
import com.acrylic.universal.files.bukkit.BukkitConfiguration;
import com.acrylic.universal.files.fileeditor.AbstractFileEditor;
import com.acrylic.universal.files.fileeditor.FileEditor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractFile implements BukkitConfiguration {

    private final File file;
    private boolean justCreated = false;
    private final JavaPlugin plugin;

    public AbstractFile(@NotNull String path) {
        this(path, null, false);
    }

    public AbstractFile(@NotNull String path, @Nullable JavaPlugin plugin) {
        this(path, plugin, true);
    }

    private AbstractFile(@NotNull String path, @Nullable JavaPlugin plugin, boolean useUniversalPluginIfNull) {
        if (useUniversalPluginIfNull) {
            path = BukkitConfiguration.getPluginConfigurationPath((plugin == null) ? Universal.getPlugin() : plugin, path);
            this.plugin = (plugin == null) ? Universal.getPlugin() : plugin;
        } else
            this.plugin = null;
        Logger logger = Logger.getGlobal();
        file = new File(path);
        try {
            if (!file.exists()) {
                justCreated = true;
                File parent = file.getParentFile();
                if (parent != null)
                    if (!parent.mkdirs())
                        logger.log(Level.WARNING, "Failed to create parent path for " + path);
                if (!file.createNewFile())
                    logger.log(Level.WARNING, "Failed to create file for " + path);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public boolean isJustCreated() {
        return justCreated;
    }

    @NotNull
    @Override
    public File getFile() {
        return file;
    }

    @NotNull
    public abstract AbstractFileEditor getFileEditor();

    public abstract void saveFile();

}
