package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.IronGolemEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitIronGolemInstance extends BukkitLivingEntityInstance implements IronGolemEntityInstance {

    private final IronGolem ironGolem;

    public BukkitIronGolemInstance(@NotNull Location location) {
        this((IronGolem) spawnEntity(location, EntityType.IRON_GOLEM));
    }

    public BukkitIronGolemInstance(@NotNull IronGolem ironGolem) {
        this.ironGolem = ironGolem;
    }

    @NotNull
    @Override
    public IronGolem getBukkitEntity() {
        return ironGolem;
    }
}
