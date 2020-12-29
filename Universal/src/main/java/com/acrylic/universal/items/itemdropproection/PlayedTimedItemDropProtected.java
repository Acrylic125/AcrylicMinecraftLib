package com.acrylic.universal.items.itemdropproection;

import com.acrylic.universal.interfaces.Timed;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

public class PlayedTimedItemDropProtected
        extends PlayerItemDropProtected
        implements Timed  {

    private long time = 0;

    public PlayedTimedItemDropProtected(@NotNull Item itemEntity, long cooldown, @NotNull Collection<UUID> allowedPlayers) {
        super(itemEntity, allowedPlayers);
        clockTime(cooldown);
    }

    public PlayedTimedItemDropProtected(@NotNull Item itemEntity, long cooldown) {
        super(itemEntity);
        clockTime(cooldown);
    }

    public PlayedTimedItemDropProtected(@NotNull Item itemEntity, long cooldown, Player... allowedPlayers) {
        super(itemEntity, allowedPlayers);
        clockTime(cooldown);
    }

    public PlayedTimedItemDropProtected(@NotNull Item itemEntity, long cooldown, @NotNull Player allowedPlayer) {
        super(itemEntity, allowedPlayer);
        clockTime(cooldown);
    }

    @Override
    public boolean isAllowedToPickup(@NotNull Entity entity) {
        return System.currentTimeMillis() >= time || super.isAllowedToPickup(entity);
    }

    @Override
    public long getLastTimed() {
        return time;
    }

    @Override
    public void setLastTimed(long time) {
        this.time = time;
    }

    @Override
    public boolean canRemoveFromMap() {
        return System.currentTimeMillis() >= time || super.canRemoveFromMap();
    }
}
