package com.acrylic.acrylic;

import com.acrylic.universal.Universal;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.text.ChatUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class Acrylic extends JavaPlugin {

    @Override
    public void onEnable() {
        Universal.setPlugin(this);

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
