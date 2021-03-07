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

}
