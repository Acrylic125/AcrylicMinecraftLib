package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.SkeletonEntityInstance;
import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitSkeletonInstance extends BukkitLivingEntityInstance implements SkeletonEntityInstance {

    private final Skeleton skeleton;

    public BukkitSkeletonInstance(@NotNull Location location) {
        this((Skeleton) spawnEntity(location, EntityType.SKELETON));
    }

    public BukkitSkeletonInstance(@NotNull Skeleton skeleton) {
        this.skeleton = skeleton;
    }

    @NotNull
    @Override
    public Skeleton getBukkitEntity() {
        return skeleton;
    }

    @Override
    public boolean isWitherSkeleton() {
        return false;
    }

    @Override
    public boolean isStray() {
        return false;
    }
}
