package com.acrylic.universal.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public interface SkeletonEntityInstance extends LivingEntityInstance {

    enum SkeletonType {
        NORMAL(EntityType.SKELETON), STRAY(EntityType.STRAY), WITHER(EntityType.WITHER_SKELETON);

        private final EntityType entityType;

        SkeletonType(EntityType entityType) {
            this.entityType = entityType;
        }

        public EntityType getEntityType() {
            return entityType;
        }
    }

    @NotNull
    @Override
    Skeleton getBukkitEntity();

    SkeletonType getSkeletonType();

}
