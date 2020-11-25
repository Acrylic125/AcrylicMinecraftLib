package com.acrylic.acrylic;

import com.acrylic.universal.Universal;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public final class Acrylic extends JavaPlugin {

    @Override
    public void onEnable() {
        Universal.setPlugin(this);
        //YamlConfiguration yml = YamlConfiguration.loadConfiguration();
        AcrylicCommand.registerMainCommand();
        System.out.println(ChatUtils.get(
                "\n" +
                        "&3AcrylicMinecraftLib has &a&lStarted&r&3!\n" +
                        "&7Developed by &bAcrylic\n" +
                        ""));
    }

    @Override
    public void onDisable() {
        System.out.println(ChatUtils.get(
                "\n" +
                        "&3AcrylicMinecraftLib has &c&lStopped&r&3!\n" +
                        "&7Developed by &bAcrylic\n" +
                        ""));
    }
}
