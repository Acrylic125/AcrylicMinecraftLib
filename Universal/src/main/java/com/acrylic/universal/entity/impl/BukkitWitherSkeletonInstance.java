package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.WitherSkeletonEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

public class BukkitWitherSkeletonInstance
        extends BukkitSkeletonInstance
        implements WitherSkeletonEntityInstance {

    public BukkitWitherSkeletonInstance(@NotNull Location location) {
        super(location, SkeletonType.WITHER);
    }

    public BukkitWitherSkeletonInstance(@NotNull WitherSkeleton skeleton) {
        super(skeleton);
    }

    @NotNull
    @Override
    public WitherSkeleton getBukkitEntity() {
        return (WitherSkeleton) super.getBukkitEntity();
    }



}
