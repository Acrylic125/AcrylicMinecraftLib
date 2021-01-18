package com.acrylic.universal;

import com.acrylic.universal.items.ItemTypeAnalyzer;
import com.acrylic.universal.items.VanillaItemTypeAnalyzer;
import com.acrylic.universal.items.itemdropproection.ItemDropChecker;
import com.acrylic.universal.regions.ChunkedRegionMap;
import com.acrylic.universal.regions.Region;
import com.acrylic.universal.regions.RegionMap;
import com.acrylic.universal.text.AbstractMessageBuilder;
import com.acrylic.universal.text.MessageBuilder;
import com.acrylic.universal.versionstore.VersionStore;
import org.jetbrains.annotations.NotNull;

public class AcrylicPlugin {

    private AbstractMessageBuilder messageBuilder = new MessageBuilder();
    private VersionStore versionStore = new VersionStore();
    private RegionMap<Region> regionMap = new ChunkedRegionMap<>();
    private ItemDropChecker itemDropChecker;
    private ItemTypeAnalyzer itemTypeAnalyzer = new VanillaItemTypeAnalyzer();

    @NotNull
    public AbstractMessageBuilder getMessageBuilder() {
        return messageBuilder;
    }

    public void setMessageBuilder(@NotNull AbstractMessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    @NotNull
    public VersionStore getVersionStore() {
        return versionStore;
    }

    public void setVersionStore(@NotNull VersionStore versionStore) {
        this.versionStore = versionStore;
    }

    @NotNull
    public ItemTypeAnalyzer getItemTypeAnalyzer() {
        return itemTypeAnalyzer;
    }

    public void setItemTypeAnalyzer(@NotNull ItemTypeAnalyzer itemTypeAnalyzer) {
        this.itemTypeAnalyzer = itemTypeAnalyzer;
    }

    @NotNull
    public RegionMap<Region> getRegionMap() {
        return regionMap;
    }

    public void setRegionMap(@NotNull RegionMap<Region> regionMap) {
        this.regionMap = regionMap;
    }

    @NotNull
    public ItemDropChecker getItemDropChecker() {
        if (itemDropChecker == null)
            throw new IllegalStateException("There is no Item Drop Checker defined in AcrylicPlugin. Either enable it in the acrylic.yml or set it manually.");
        return itemDropChecker;
    }

    public void setItemDropChecker(@NotNull ItemDropChecker itemDropChecker) {
        this.itemDropChecker = itemDropChecker;
    }

}
