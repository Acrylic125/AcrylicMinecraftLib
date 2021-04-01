package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.StrayEntityInstance;
import com.acrylic.universal.entity.WitherSkeletonEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.Stray;
import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

public class BukkitStrayInstance
        extends BukkitSkeletonInstance
        implements StrayEntityInstance {

    public BukkitStrayInstance(@NotNull Location location) {
        super(location, SkeletonType.STRAY);
    }

    public BukkitStrayInstance(@NotNull Stray skeleton) {
        super(skeleton);
    }

    @NotNull
    @Override
    public Stray getBukkitEntity() {
        return (Stray) super.getBukkitEntity();
    }

}
