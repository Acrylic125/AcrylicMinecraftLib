package com.acrylic.universal.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface ZombieEntityInstance extends LivingEntityInstance {

    enum ZombieType {
        NORMAL(EntityType.ZOMBIE), HUSK(EntityType.HUSK), VILLAGER(EntityType.ZOMBIE_VILLAGER);

        private final EntityType entityType;

        ZombieType(EntityType entityType) {
            this.entityType = entityType;
        }

        public EntityType getEntityType() {
            return entityType;
        }
    }

    @NotNull
    @Override
    Zombie getBukkitEntity();

    default ZombieType getZombieType() {
        return ZombieType.NORMAL;
    }

}
