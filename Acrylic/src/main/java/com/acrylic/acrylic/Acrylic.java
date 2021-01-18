package com.acrylic.acrylic;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.AcrylicPlugin;
import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.events.ArmorChangeListener;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.files.configloader.ConfigLoader;
import com.acrylic.universal.items.ItemUtils;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.regions.RegionDisplayPointerAnimation;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.versionstore.VersionStore;
import com.acrylic.version_1_8.items.VanillaItemTypeAnalyzer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Acrylic extends JavaPlugin {

    private static void loadInMessage(@NotNull Runnable runnable, @NotNull String name) {
        loadInMessage(0, runnable, name);
    }

    private static void loadInMessage(int indent, @NotNull Runnable runnable, @NotNull String name) {
        sendConsoleMessage(indent, "Loading in: " + name);
        runnable.run();
        sendConsoleMessage(indent, "| Completed: " + name);
    }

    private static void sendConsoleMessage(@NotNull String msg) {
        sendConsoleMessage(0, msg);
    }

    private static void sendConsoleMessage(int indent, @NotNull String msg) {
        StringBuilder indentSpace = new StringBuilder();
        if (indent > 0)
            for (int i = 0; i < indent; i++)
                indentSpace.append("|   ");
        System.out.println(indentSpace.toString() + msg);
    }

    @Override
    public void onEnable() {
        Universal.setPlugin(this);
        load();
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

    private void load() {
        AcrylicCommand.registerMainCommand();
        loadInMessage(() -> Universal.setAcrylicPlugin(new AcrylicPlugin()), "Basics");
        loadInMessage(() -> Universal.setTimingManager(TimingManager.of(this)), "Aikar's Timings");
        loadInMessage(this::loadConfigurables, "Config");
        loadInMessage(this::versionCheck, "Version Check");
    }

    private void loadConfigurables() {
        Configuration configuration = new Configuration("acrylic.yml", this);
        configuration.loadFromResources(this);
        loadInMessage(1, () -> {
            ArmorChangeListener armorChangeListener = new ArmorChangeListener();
            ConfigLoader.getLoader(ArmorChangeListener.class).loadObjectThenSave(armorChangeListener);
        }, "Armor Change Listener");
        loadInMessage(1, () -> ConfigLoader.getLoader(Dangle.class).staticLoadThenSave(), "Dangle");
        loadInMessage(1, () -> ConfigLoader.getLoader(RegionDisplayPointerAnimation.class).staticLoadThenSave(), "Region Pointer Animation");
        loadInMessage(1, () -> {
            sendConsoleMessage(1, "Checking Item Drop Protection.");
            configuration.getFileEditor().getFileEditor("item-drop-protection").safeFileAccess(fileEditor -> {
                if (fileEditor.getBoolean("use-default-implementation")) {
                    Universal.getAcrylicPlugin().setItemDropChecker(new ItemDropChecker());
                    sendConsoleMessage(1, "Using default item drop checker implementation.");
                }
            });
        }, "Item Drop Protection");
    }

    private void versionCheck() {
        sendConsoleMessage(1, "Checking for legacy version (1.8)");
        VersionStore versionStore = Universal.getAcrylicPlugin().getVersionStore();;
        if (versionStore.isLegacyVersion()) {
            sendConsoleMessage(1, "Using 1.8!");
            Universal.getAcrylicPlugin().setItemTypeAnalyzer(new VanillaItemTypeAnalyzer());
            sendConsoleMessage(1,  "Using " + VanillaItemTypeAnalyzer.class.getName() + ".");
        }
        sendConsoleMessage(1, "Checking other version initializers.");
    }

}
