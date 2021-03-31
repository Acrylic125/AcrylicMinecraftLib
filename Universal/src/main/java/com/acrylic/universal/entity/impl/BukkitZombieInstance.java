package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitZombieInstance extends BukkitLivingEntityInstance implements ZombieEntityInstance {

    private final Zombie zombie;

    public BukkitZombieInstance(@NotNull Location location) {
        this((Zombie) spawnEntity(location, EntityType.ZOMBIE));
    }

    public BukkitZombieInstance(@NotNull Zombie zombie) {
        this.zombie = zombie;
    }

    @Override
    public boolean isHusk() {
        return false;
    }

    @NotNull
    @Override
    public Zombie getBukkitEntity() {
        return zombie;
    }
}
