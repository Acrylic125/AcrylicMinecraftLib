package com.acrylic.universal.entity;

import org.bukkit.entity.ArmorStand;
import org.jetbrains.annotations.NotNull;

public interface ArmorStandInstance extends LivingEntityInstance {

    @NotNull
    @Override
    ArmorStand getBukkitEntity();

    boolean hasArms();

    void setArms(boolean arms);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean hasGravity();

    void setGravity(boolean gravity);

    boolean isSmall();

    void setSmall(boolean small);

    boolean isMarker();

    void setMarker(boolean marker);

    default void asAnimator() {
        setVisible(false);
        setArms(false);
        setBasePlate(false);
        setGravity(false);
    }

    default void asHologram() {
        asAnimator();
        setMarker(true);
        setSmall(true);
    }

    class Builder extends LivingEntityBuilder<Builder> {

        private final ArmorStandInstance armorStandInstance;

        public Builder(ArmorStandInstance armorStandInstance) {
            this.armorStandInstance = armorStandInstance;
        }

        public Builder arms(boolean arms) {
            this.armorStandInstance.setArms(arms);
            return this;
        }

        public Builder basePlate(boolean basePlate) {
            this.armorStandInstance.setBasePlate(basePlate);
            return this;
        }

        public Builder gravity(boolean gravity) {
            this.armorStandInstance.setGravity(gravity);
            return this;
        }

        public Builder small(boolean small) {
            this.armorStandInstance.setSmall(small);
            return this;
        }

        public Builder marker(boolean marker) {
            this.armorStandInstance.setMarker(marker);
            return this;
        }

        public Builder asAnimator() {
            this.armorStandInstance.asAnimator();
            return this;
        }

        public Builder asHologram() {
            this.armorStandInstance.asHologram();
            return this;
        }

        @Override
        public ArmorStand buildEntity() {
            return armorStandInstance.getBukkitEntity();
        }

        @Override
        public ArmorStandInstance buildEntityInstance() {
            return armorStandInstance;
        }

        @Override
        public ArmorStandInstance getBuildFrom() {
            return armorStandInstance;
        }
    }

}
