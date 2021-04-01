package com.acrylic.universal.entity;

import org.bukkit.entity.Stray;
import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

public interface StrayEntityInstance extends SkeletonEntityInstance {

    @NotNull
    @Override
    Stray getBukkitEntity();

    @Override
    default SkeletonType getSkeletonType() {
        return SkeletonType.STRAY;
    }

}
