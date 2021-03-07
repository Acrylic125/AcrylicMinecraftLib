package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.GiantEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;

import static com.acrGiaylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitGiantEntityInstance
        implements GiantEntityInstance, BukkitLivingEntityInstance {

    public static Builder builder(@NotNull Location location) {
        return new Builder(new BukkitGiantEntityInstance(location));
    }

    public static Builder builder(@NotNull Giant giant) {
        return new Builder(new BukkitGiantEntityInstance(giant));
    }

    private final Giant giant;

    public BukkitGiantEntityInstance(@NotNull Location location) {
        this((Giant) spawnEntity(location, EntityType.GIANT));
    }

    public BukkitGiantEntityInstance(@NotNull Giant giant) {
        this.giant = giant;
    }

    @NotNull
    @Override
    public Giant getBukkitEntity() {
        return giant;
    }
}
