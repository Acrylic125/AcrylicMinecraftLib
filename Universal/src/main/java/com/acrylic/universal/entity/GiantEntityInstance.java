package com.acrylic.universal.entity;

import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;

public interface GiantEntityInstance extends LivingEntityInstance {

    @NotNull
    @Override
    Giant getBukkitEntity();

    class Builder extends LivingEntityBuilder<Builder> {

        private final GiantEntityInstance giantInstance;

        public Builder(GiantEntityInstance giantInstance) {
            this.giantInstance = giantInstance;
        }

        @Override
        public Giant buildEntity() {
            return giantInstance.getBukkitEntity();
        }

        @Override
        public GiantEntityInstance buildEntityInstance() {
            return giantInstance;
        }

        @Override
        public GiantEntityInstance getBuildFrom() {
            return giantInstance;
        }
    }

}
