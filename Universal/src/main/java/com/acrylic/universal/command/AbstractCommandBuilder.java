package com.acrylic.universal.command;

import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;

public interface AbstractCommandBuilder extends CommandBuilderExecutor {

    void register(JavaPlugin plugin);

    default void register() {
        register(Universal.getPlugin());
    }

}
