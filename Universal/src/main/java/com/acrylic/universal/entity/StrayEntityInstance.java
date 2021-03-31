package com.acrylic.universal.entity;

import org.bukkit.entity.Stray;
import org.jetbrains.annotations.NotNull;

public interface StrayEntityInstance extends SkeletonEntityInstance {

    @NotNull
    @Override
    Stray getBukkitEntity();

    @Override
    default boolean isWitherSkeleton() {
        return false;
    }

    @Override
    default boolean isStray() {
        return true;
    }
}
