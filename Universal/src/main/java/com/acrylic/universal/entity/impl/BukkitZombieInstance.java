package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitZombieInstance
        extends BukkitLivingEntityInstance
        implements ZombieEntityInstance {

    private final Zombie zombie;
    private final ZombieType zombieType;

    public BukkitZombieInstance(@NotNull Location location) {
        this(location, ZombieType.NORMAL);
    }

    public BukkitZombieInstance(@NotNull Location location, ZombieType zombieType) {
        this.zombie = spawnEntity(location, zombieType.getEntityType());
        this.zombieType = zombieType;
    }

    public BukkitZombieInstance(@NotNull Zombie zombie) {
        this.zombie = zombie;
        if (zombie instanceof Husk) {
            this.zombieType = ZombieType.HUSK;
        } else if (zombie instanceof ZombieVillager) {
            this.zombieType = ZombieType.VILLAGER;
        } else {
            this.zombieType = ZombieType.NORMAL;
        }
    }

    @NotNull
    @Override
    public Zombie getBukkitEntity() {
        return zombie;
    }

    @Override
    public ZombieType getZombieType() {
        return zombieType;
    }
}
