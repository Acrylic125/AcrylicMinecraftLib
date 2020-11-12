package com.acrylic.acrylic.command;

import com.acrylic.acrylic.Acrylic;
import org.bukkit.plugin.java.JavaPlugin;

public interface AbstractCommandBuilder extends CommandBuilderExecutor {

    void register(JavaPlugin plugin);

    default void register() {
        register(Acrylic.getPlugin());
    }

}
