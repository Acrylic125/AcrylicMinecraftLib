package com.acrylic.universal.interfaces;

import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;

public interface PluginRegister {

    void register(JavaPlugin javaPlugin);

    default void register() {
        register(Universal.getPlugin());
    }

}
