package com.acrylic.universal.entity;

import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

/**
 * Why is this named old?
 *
 * In newer versions of the Bukkit API, the switch to
 * a proper instance instance of Wither Skeleton was made
 *
 * @see org.bukkit.entity.WitherSkeleton
 * @see WitherSkeletonEntityInstance
 *
 * Please use {@link WitherSkeletonEntityInstance} if the
 * {@link org.bukkit.entity.WitherSkeleton} instance is
 * available in THAT version of the Bukkit API.
 */
public interface WitherSkeletonEntityInstanceOld extends SkeletonEntityInstance {

    @NotNull
    @Override
    Skeleton getBukkitEntity();

}
