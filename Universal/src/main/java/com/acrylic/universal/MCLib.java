package com.acrylic.universal;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.factory.EntityFactoryImpl;
import com.acrylic.universal.factory.UtilityFactory;
import com.acrylic.universal.factory.UtilityFactoryImpl;
import com.acrylic.universal.files.configloader.ConfigValue;
import com.acrylic.universal.files.configloader.Configurable;
import com.acrylic.universal.interfaces.Terminable;
import com.acrylic.universal.items.ItemTypeAnalyzer;
import com.acrylic.universal.items.VanillaItemTypeAnalyzer;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.worldblocksaver.BlockSaveObserver;
import com.acrylic.universal.worldblocksaver.BlockSaver;
import com.acrylic.universal.worldblocksaver.SerializedBlockSaveInstance;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Configurable(
        plugin = "Acrylic",
        filePath = "acrylic.yml")
public class MCLib implements Terminable {

    private static JavaPlugin plugin;
    private static MCLib mcLib;
    private static TimingManager timingManager;
    private static final int LEGACY_VERSION = 8;
    @ConfigValue(path = {"item-drop-protection", "use-default-implementation"})
    public static boolean ITEM_DROP_PROTECTION = true;
    @ConfigValue(path = {"block-saver", "use-default-implementation"})
    public static boolean BLOCK_SAVER = true;

    private final short version = (short) Integer.parseInt(Bukkit.getVersion().split("\\.")[1]);
    private ItemDropChecker itemDropChecker;
    private ItemTypeAnalyzer itemTypeAnalyzer = new VanillaItemTypeAnalyzer();
    private BlockSaver<SerializedBlockSaveInstance, BlockSaveObserver> blockSaver;
    private UtilityFactory utilityFactory = new UtilityFactoryImpl();
    private EntityFactory entityFactory = new EntityFactoryImpl();

    public ItemTypeAnalyzer getItemTypeAnalyzer() {
        return itemTypeAnalyzer;
    }

    public void setItemTypeAnalyzer(@NotNull ItemTypeAnalyzer itemTypeAnalyzer) {
        this.itemTypeAnalyzer = itemTypeAnalyzer;
    }

    public ItemDropChecker getItemDropChecker() {
        if (itemDropChecker == null)
            throw new IllegalStateException("There is no Item Drop Checker defined in AcrylicPlugin. Either enable it in the acrylic.yml or set it manually.");
        return itemDropChecker;
    }

    public void setItemDropChecker(@NotNull ItemDropChecker itemDropChecker) {
        this.itemDropChecker = itemDropChecker;
    }

    public BlockSaver<SerializedBlockSaveInstance, BlockSaveObserver> getBlockSaver() {
        if (blockSaver == null)
            throw new IllegalStateException("There is no Block Saver defined in AcrylicPlugin. Either enable it in the acrylic.yml or set it manually.");
        return blockSaver;
    }

    public void setBlockSaver(@NotNull BlockSaver<SerializedBlockSaveInstance, BlockSaveObserver> blockSaver) {
        this.blockSaver = blockSaver;
    }

    public UtilityFactory getUtilityFactory() {
        return utilityFactory;
    }

    public void setUtilityFactory(@NotNull UtilityFactory utilityFactory) {
        this.utilityFactory = utilityFactory;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    public void setEntityFactory(@NotNull EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

    @Override
    public void terminate() {
        if (blockSaver != null) {
            blockSaver.saveToFile();
            blockSaver.terminate();
        }
    }

    public static void setPlugin(@NotNull JavaPlugin plugin) {
        MCLib.plugin = plugin;
    }

    @NotNull
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setTimingManager(@NotNull TimingManager timingManager) {
        MCLib.timingManager = timingManager;
    }

    public static TimingManager getTimingManager() {
        return timingManager;
    }

    public static void setMCLib(@NotNull MCLib mcLib) {
        MCLib.mcLib = mcLib;
    }

    public static MCLib getLib() {
        return mcLib;
    }

    public short getVersion() {
        return version;
    }

    public boolean isLegacyVersion() {
        return getVersion() <= LEGACY_VERSION;
    }

}
