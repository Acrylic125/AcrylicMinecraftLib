package com.acrylic.acrylic;

import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.acrylic.text.ChatUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Acrylic extends JavaPlugin {

    /**
     * Plugin Instance.
     */
    @Getter
    private static Acrylic plugin;

    @Override
    public void onEnable() {
        plugin = this;

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
