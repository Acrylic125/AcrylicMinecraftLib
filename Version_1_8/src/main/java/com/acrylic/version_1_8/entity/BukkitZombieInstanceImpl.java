package com.acrylic.version_1_8.entity;

import com.acrylic.universal.entity.EntityInstance;
import com.acrylic.universal.entity.SkeletonEntityInstance;
import com.acrylic.universal.entity.ZombieEntityInstance;
import com.acrylic.universal.entity.impl.BukkitLivingEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitZombieInstanceImpl
        extends BukkitLivingEntityInstance
        implements ZombieEntityInstance {

    private final Zombie zombie;
    private final ZombieType zombieType;

    public BukkitZombieInstanceImpl(@NotNull Location location, @NotNull ZombieType zombieType) {
        this.zombie = spawnEntity(location, EntityType.ZOMBIE);
        switch (zombieType) {
            case VILLAGER:
                zombie.setVillager(true);
                break;
            case HUSK:
                throw new IllegalArgumentException("Husk is not a valid entity type in 1.8.");
        }
        this.zombieType = zombieType;
    }


    public BukkitZombieInstanceImpl(@NotNull Zombie zombie) {
        this.zombie = zombie;
        this.zombieType = (zombie.isVillager()) ?
                ZombieType.VILLAGER : ZombieType.NORMAL;
    }

    @Override
    public ZombieType getZombieType() {
        return zombieType;
    }

    @NotNull
    @Override
    public Zombie getBukkitEntity() {
        return zombie;
    }
}
