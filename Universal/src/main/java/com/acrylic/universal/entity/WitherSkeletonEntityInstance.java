package com.acrylic.universal.entity;

import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

public interface WitherSkeletonEntityInstance extends SkeletonEntityInstance {

    @NotNull
    @Override
    WitherSkeleton getBukkitEntity();

}
