package com.acrylic.universal;

import co.aikar.timings.lib.TimingManager;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.regions.ChunkedRegionMap;
import com.acrylic.universal.regions.RegionMap;
import com.acrylic.universal.regions.SimpleRegionMap;
import com.acrylic.universal.regions.Region;
import com.acrylic.universal.text.AbstractMessageBuilder;
import com.acrylic.universal.text.MessageBuilder;
import com.acrylic.universal.versionstore.AbstractVersionStore;
import com.acrylic.universal.versionstore.VersionStore;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Universal {

    private static JavaPlugin plugin;
    private static AcrylicPlugin acrylicPlugin;
    private static TimingManager timingManager;

    public static void setPlugin(@NotNull JavaPlugin plugin) {
        Universal.plugin = plugin;
    }

    @NotNull
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    @NotNull
    public static AcrylicPlugin getAcrylicPlugin() {
        return acrylicPlugin;
    }

    public static void setAcrylicPlugin(@NotNull AcrylicPlugin acrylicPlugin) {
        Universal.acrylicPlugin = acrylicPlugin;
    }

    public static void setTimingManager(@NotNull TimingManager timingManager) {
        Universal.timingManager = timingManager;
    }

    public static TimingManager getTimingManager() {
        return timingManager;
    }

}
