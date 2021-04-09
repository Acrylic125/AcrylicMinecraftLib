package com.acrylic.acrylic;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.acrylic.defaultcommands.AcrylicCommand;
import com.acrylic.universal.MCLib;
import com.acrylic.universal.animations.dangle.Dangle;
import com.acrylic.universal.events.ArmorChangeListener;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.files.configloader.ConfigLoader;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.regions.RegionDisplayPointerAnimation;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.worldblocksaver.SimpleBlockSaveSerializer;
import com.acrylic.universal.worldblocksaver.SimpleBlockSaver;
import com.acrylic.version_1_8.factory.EntityFactoryImpl;
import com.acrylic.version_1_8.factory.UtilityFactoryImpl;
import com.acrylic.version_1_8.items.VanillaItemTypeAnalyzer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Acrylic extends JavaPlugin {

    @Override
    public void onEnable() {
        MCLib.setPlugin(this);
        load();
        System.out.println(ChatUtils.get(
                "\n" +
                        "&3AcrylicMinecraftLib has &a&lStarted&r&3!\n" +
                        "&7Developed by &bAcrylic\n" +
                        ""));
    }

    @Override
    public void onDisable() {
        MCLib.getLib().terminate();
        System.out.println(ChatUtils.get(
                "\n" +
                        "&3AcrylicMinecraftLib has &c&lStopped&r&3!\n" +
                        "&7Developed by &bAcrylic\n" +
                        ""));
    }

    private void load() {
        ConfigLoader.getLoader(MCLib.class).staticLoadThenSave();
        AcrylicCommand.registerMainCommand();
        MCLib.setMCLib(new MCLib());
        MCLib.setTimingManager(TimingManager.of(this));
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
        if (MCLib.ITEM_DROP_PROTECTION) {
            MCLib.getLib().setItemDropChecker(new ItemDropChecker());
        }
        if (MCLib.BLOCK_SAVER) {
            MCLib.getLib().setBlockSaver(new SimpleBlockSaver());
        }
    }

    private void versionCheck() {
        loadBlockSaver();
        legacyCheck();
    }

    private void legacyCheck() {
        MCLib lib = MCLib.getLib();
        if (lib.isLegacyVersion()) {
            lib.setItemTypeAnalyzer(new VanillaItemTypeAnalyzer());
            lib.setEntityFactory(new EntityFactoryImpl());
        }
        if (lib.getVersion() <= 13) {
            lib.setUtilityFactory(new UtilityFactoryImpl());
        }
    }

    private void loadBlockSaver() {
        if (MCLib.BLOCK_SAVER) {
            SimpleBlockSaver blockSaver = new SimpleBlockSaver();
            MCLib.getLib().setBlockSaver(blockSaver);
            blockSaver.setSerializer(new SimpleBlockSaveSerializer());
            blockSaver.restoreAllCached();
            blockSaver.start();
        }
    }

}
