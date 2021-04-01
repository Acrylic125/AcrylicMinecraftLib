package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.SkeletonEntityInstance;
import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitSkeletonInstance
        extends BukkitLivingEntityInstance
        implements SkeletonEntityInstance {

    private final Skeleton skeleton;
    private final SkeletonType skeletonType;

    public BukkitSkeletonInstance(@NotNull Location location) {
        this(location, SkeletonType.NORMAL);
    }

    public BukkitSkeletonInstance(@NotNull Location location, @NotNull SkeletonType skeletonType) {
        this.skeleton = spawnEntity(location, skeletonType.getEntityType());
        this.skeletonType = skeletonType;
    }

    public BukkitSkeletonInstance(@NotNull Skeleton skeleton) {
        this.skeleton = skeleton;
        if (skeleton instanceof WitherSkeleton) {
            this.skeletonType = SkeletonType.WITHER;
        } else if (skeleton instanceof Stray) {
            this.skeletonType = SkeletonType.STRAY;
        } else {
            this.skeletonType = SkeletonType.NORMAL;
        }
    }

    @NotNull
    @Override
    public Skeleton getBukkitEntity() {
        return skeleton;
    }

    @Override
    public SkeletonType getSkeletonType() {
        return skeletonType;
    }

}
