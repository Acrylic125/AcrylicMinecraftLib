package com.acrylic.universal.items.itemdropproection;

import com.acrylic.universal.MCLib;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

public interface ItemDropProtected {

    Item getItemEntity();

    boolean isAllowedToPickup(@NotNull Entity player);

    boolean canRemoveFromMap();

    @NotNull
    static PlayedTimedItemDropProtected getDropProtectedItem(@NotNull Item item, long cooldown) throws IllegalStateException {
        PlayedTimedItemDropProtected playerItemDropProtected = new PlayedTimedItemDropProtected(item, cooldown);
        MCLib.getLib().getItemDropChecker().getItemDropProtectionMap().register(playerItemDropProtected);
        return playerItemDropProtected;
    }

    @NotNull
    static PlayerItemDropProtected getDropProtectedItem(@NotNull Item item) throws IllegalStateException {
        PlayerItemDropProtected playerItemDropProtected = new PlayerItemDropProtected(item);
        MCLib.getLib().getItemDropChecker().getItemDropProtectionMap().register(playerItemDropProtected);
        return playerItemDropProtected;
    }

}
