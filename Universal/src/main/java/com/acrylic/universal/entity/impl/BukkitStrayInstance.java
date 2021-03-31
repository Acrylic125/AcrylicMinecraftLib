package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.StrayEntityInstance;
import com.acrylic.universal.entity.WitherSkeletonEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Stray;
import org.bukkit.entity.WitherSkeleton;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitStrayInstance extends BukkitLivingEntityInstance implements StrayEntityInstance {

    private final Stray skeleton;

    public BukkitStrayInstance(@NotNull Location location) {
        this((Stray) spawnEntity(location, EntityType.STRAY));
    }

    public BukkitStrayInstance(@NotNull Stray skeleton) {
        this.skeleton = skeleton;
    }

    @NotNull
    @Override
    public Stray getBukkitEntity() {
        return skeleton;
    }

}
