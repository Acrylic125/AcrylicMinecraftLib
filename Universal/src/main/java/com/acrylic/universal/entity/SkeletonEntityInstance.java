package com.acrylic.universal.entity;

import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public interface SkeletonEntityInstance extends LivingEntityInstance {

    @NotNull
    @Override
    Skeleton getBukkitEntity();

    boolean isWitherSkeleton();

    boolean isStray();

}
