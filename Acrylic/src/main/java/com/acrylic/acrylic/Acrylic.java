package com.acrylic.acrylic;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.AcrylicPlugin;
import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.events.ArmorChangeEvent;
import com.acrylic.universal.events.ArmorChangeListener;
import com.acrylic.universal.events.EventBuilder;
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
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Acrylic extends JavaPlugin {

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
        Universal.setAcrylicPlugin(new AcrylicPlugin());
        Universal.setTimingManager(TimingManager.of(this));
        loadConfigurables();
        versionCheck();
    }

    private void loadConfigurables() {
        Configuration configuration = new Configuration("acrylic.yml", this);
        configuration.loadFromResources(this);
        ArmorChangeListener armorChangeListener = new ArmorChangeListener();
        ConfigLoader.getLoader(ArmorChangeListener.class).loadObjectThenSave(armorChangeListener);
        ConfigLoader.getLoader(Dangle.class).staticLoadThenSave();
        ConfigLoader.getLoader(RegionDisplayPointerAnimation.class).staticLoadThenSave();
        if (AcrylicPlugin.ITEM_DROP_PROTECTION) {
            Universal.getAcrylicPlugin().setItemDropChecker(new ItemDropChecker());
        }
    }

    private void versionCheck() {
        VersionStore versionStore = Universal.getAcrylicPlugin().getVersionStore();
        //
        legacyCheck(versionStore);
        //
        loadBlockSaver();
    }

    private void legacyCheck(VersionStore versionStore) {
        AcrylicPlugin acrylicPlugin = Universal.getAcrylicPlugin();
        if (versionStore.isLegacyVersion()) {
            acrylicPlugin.setItemTypeAnalyzer(new VanillaItemTypeAnalyzer());
        }
        if (versionStore.getVersion() <= 13) {
            acrylicPlugin.setBlockFactory(new SimpleBlockFactory());
        }
    }

    private void loadBlockSaver() {
        if (AcrylicPlugin.BLOCK_SAVER) {
            SimpleBlockSaver blockSaver = new SimpleBlockSaver();
            Universal.getAcrylicPlugin().setBlockSaver(blockSaver);
            blockSaver.setSerializer(new SimpleBlockSaveSerializer());
            blockSaver.restoreAllCached();
            blockSaver.start();
        }
    }

}
