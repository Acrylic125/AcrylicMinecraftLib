package com.acrylic.universal.interfaces;

import com.acrylic.universal.MCLib;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface PluginRegister {

    void register(@NotNull JavaPlugin javaPlugin);

    default void register() {
        register(MCLib.getPlugin());
    }

}
