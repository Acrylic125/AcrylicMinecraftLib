package com.acrylic.version_1_8.entity;

import com.acrylic.universal.entity.WitherSkeletonEntityInstanceOld;
import org.bukkit.Location;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public class BukkitWitherSkeletonInstanceOldImpl
        extends BukkitSkeletonInstanceImpl
        implements WitherSkeletonEntityInstanceOld {

    public BukkitWitherSkeletonInstanceOldImpl(@NotNull Location location) {
        super(location, SkeletonType.WITHER);
    }

    public BukkitWitherSkeletonInstanceOldImpl(@NotNull Skeleton skeleton) {
        super(skeleton);
    }
}
