package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.SkeletonEntityInstance;
import com.acrylic.universal.entity.WitherSkeletonEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitWitherSkeletonInstance extends BukkitLivingEntityInstance implements WitherSkeletonEntityInstance {

    private final WitherSkeleton skeleton;

    public BukkitWitherSkeletonInstance(@NotNull Location location) {
        this((WitherSkeleton) spawnEntity(location, EntityType.WITHER_SKELETON));
    }

    public BukkitWitherSkeletonInstance(@NotNull WitherSkeleton skeleton) {
        this.skeleton = skeleton;
    }

    @NotNull
    @Override
    public WitherSkeleton getBukkitEntity() {
        return skeleton;
    }

}
