package com.acrylic.acrylic;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.AcrylicPlugin;
import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.events.ArmorChangeListener;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.files.configloader.ConfigLoader;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.regions.RegionDisplayPointerAnimation;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.versionstore.VersionStore;
import com.acrylic.universal.worldblocksaver.SimpleBlockSaveSerializer;
import com.acrylic.universal.worldblocksaver.SimpleBlockSaver;
import com.acrylic.version_1_8.blocks.SimpleBlockFactory;
import com.acrylic.version_1_8.items.VanillaItemTypeAnalyzer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Acrylic extends JavaPlugin {

    private static void loadInMessage(@NotNull Runnable runnable, @NotNull String name) {
        loadInMessage(0, runnable, name);
    }

    private static void loadInMessage(int indent, @NotNull Runnable runnable, @NotNull String name) {
        sendConsoleMessage(indent, "Loading in: " + name);
        runnable.run();
        sendConsoleMessage(indent, 1, "Completed: " + name);
    }

    private static void sendConsoleMessage(@NotNull String msg) {
        sendConsoleMessage(0, msg);
    }

    private static void sendConsoleMessage(int indent, @NotNull String msg) {
        sendConsoleMessage(indent, 0, msg);
    }

    private static void sendConsoleMessage(int indent, int subIndent, @NotNull String msg) {
        StringBuilder indentSpace = new StringBuilder();
        if (indent > 0)
            for (int i = 0; i < indent; i++)
                indentSpace.append("|   ");
        if (subIndent > 0)
            for (int i = 0; i < subIndent; i++)
                indentSpace.append("| ");
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
        Universal.getAcrylicPlugin().terminate();
        System.out.println(ChatUtils.get(
                "\n" +
                        "&3AcrylicMinecraftLib has &c&lStopped&r&3!\n" +
                        "&7Developed by &bAcrylic\n" +
                        ""));
    }

    private void load() {
        ConfigLoader.getLoader(AcrylicPlugin.class).staticLoadThenSave();
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
            sendConsoleMessage(1,1, "Checking Item Drop Protection.");
            if (AcrylicPlugin.ITEM_DROP_PROTECTION) {
                Universal.getAcrylicPlugin().setItemDropChecker(new ItemDropChecker());
                sendConsoleMessage(1, 1, "Using default item drop checker implementation.");
            } else
                sendConsoleMessage(1, 1, "Not using default item drop checker.");
        }, "Item Drop Protection");
        loadBlockSaver();
    }

    private void versionCheck() {
        sendConsoleMessage(1,"Checking for legacy version (1.8)");
        VersionStore versionStore = Universal.getAcrylicPlugin().getVersionStore();
        //
        legacyCheck(versionStore);
        sendConsoleMessage(1, "Checking other version initializers. (1." + versionStore.getVersion() + ")");
        //
    }

    private void legacyCheck(VersionStore versionStore) {
        AcrylicPlugin acrylicPlugin = Universal.getAcrylicPlugin();
        if (versionStore.isLegacyVersion()) {
            sendConsoleMessage(1, "Using Legacy!");
            acrylicPlugin.setItemTypeAnalyzer(new VanillaItemTypeAnalyzer());
            sendConsoleMessage(1,1, "Using " + VanillaItemTypeAnalyzer.class.getName() + ".");
        }
        if (versionStore.getVersion() <= 13) {
            sendConsoleMessage(1, "Using block data!");
            acrylicPlugin.setBlockFactory(new SimpleBlockFactory());
            sendConsoleMessage(1,1, "Using " + SimpleBlockFactory.class.getName() + ".");
        }
    }

    private void loadBlockSaver() {
            loadInMessage(1, () -> {
                sendConsoleMessage(1,1, "Checking Block Saver.");
                if (AcrylicPlugin.BLOCK_SAVER) {
                    SimpleBlockSaver blockSaver = new SimpleBlockSaver();
                    Universal.getAcrylicPlugin().setBlockSaver(blockSaver);
                    blockSaver.setSerializer(new SimpleBlockSaveSerializer());
                    sendConsoleMessage(1, 1, "Block Saver Restoration has started!");
                    blockSaver.restoreAllCached();
                    sendConsoleMessage(2, 1, "Block Saver Restoration Complete!");
                    blockSaver.start();
                    blockSaver.observe(AcrylicCommand.test);
                    sendConsoleMessage(1, 1, "Using default block saver implementation.");
                } else
                    sendConsoleMessage(1, 1, "Not using default block saver.");
            }, "Block Saver");
    }

}
