package com.acrylic.universal.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface SpiderEntityInstance extends LivingEntityInstance {

    enum SpiderType {
        NORMAL(EntityType.SPIDER), CAVE_SPIDER(EntityType.CAVE_SPIDER);

        private final EntityType entityType;

        SpiderType(EntityType entityType) {
            this.entityType = entityType;
        }

        public EntityType getEntityType() {
            return entityType;
        }
    }

    @NotNull
    @Override
    Spider getBukkitEntity();

    SpiderType getSpiderType();

}
