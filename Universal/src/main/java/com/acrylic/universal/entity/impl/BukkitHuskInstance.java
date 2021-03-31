package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.HuskEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitHuskInstance extends BukkitLivingEntityInstance implements HuskEntityInstance {

    private final Husk zombie;

    public BukkitHuskInstance(@NotNull Location location) {
        this((Husk) spawnEntity(location, EntityType.HUSK));
    }

    public BukkitHuskInstance(@NotNull Husk zombie) {
        this.zombie = zombie;
    }

    @NotNull
    @Override
    public Husk getBukkitEntity() {
        return zombie;
    }
}
