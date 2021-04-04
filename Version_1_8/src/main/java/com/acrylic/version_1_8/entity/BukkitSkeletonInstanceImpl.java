package com.acrylic.version_1_8.entity;

import com.acrylic.universal.entity.SkeletonEntityInstance;
import com.acrylic.universal.entity.impl.BukkitLivingEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitSkeletonInstanceImpl
        extends BukkitLivingEntityInstance
        implements SkeletonEntityInstance {

    private final Skeleton skeleton;
    private final SkeletonType skeletonType;

    public BukkitSkeletonInstanceImpl(@NotNull Location location) {
        this(location, SkeletonType.NORMAL);
    }

    public BukkitSkeletonInstanceImpl(@NotNull Location location, @NotNull SkeletonType skeletonType) {
        this.skeleton = spawnEntity(location, EntityType.SKELETON);
        switch (skeletonType) {
            case WITHER:
                skeleton.setSkeletonType(Skeleton.SkeletonType.WITHER);
                break;
            case STRAY:
                throw new IllegalArgumentException("Stray is not a valid entity type in 1.8.");
        }
        this.skeletonType = skeletonType;
    }


    public BukkitSkeletonInstanceImpl(@NotNull Skeleton skeleton) {
        this.skeleton = skeleton;
        this.skeletonType = (skeleton.getSkeletonType().equals(Skeleton.SkeletonType.WITHER)) ?
                SkeletonType.WITHER : SkeletonType.NORMAL;
    }

    @Override
    public SkeletonType getSkeletonType() {
        return skeletonType;
    }

    @NotNull
    @Override
    public Skeleton getBukkitEntity() {
        return skeleton;
    }
}
