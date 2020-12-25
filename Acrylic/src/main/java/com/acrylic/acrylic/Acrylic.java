package com.acrylic.acrylic;

import com.acrylic.universal.Universal;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.events.ArmorChangeListener;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.items.ItemUtils;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.items.VanillaItemTypeAnalyzer;
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
        AcrylicCommand.registerMainCommand();
        System.out.println("Loading Config...");
        Configuration configuration = new Configuration("acrylic.yml", this);
        configuration.loadFromResources(this);
        System.out.println("Checking for legacy version (1.8)");
        if (Universal.getVersionStore().isLegacyVersion()) {
            System.out.println("Using 1.8!");
            ItemUtils.setItemTypeAnalyzer(new VanillaItemTypeAnalyzer());
            System.out.println("Loaded " + VanillaItemTypeAnalyzer.class.getName() + ".");
        }
        System.out.println("Loading Armor Change Listener.");
        new ArmorChangeListener(configuration);
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
