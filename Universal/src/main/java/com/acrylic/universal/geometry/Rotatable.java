package com.acrylic.universal.geometry;

import com.acrylic.universal.math.Orientation3D;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface Rotatable {

    @NotNull
    Orientation3D getOrientation();

    void setOrientation(@NotNull Orientation3D orientation);

    default void modifyOrientationRelativeTo(@NotNull LivingEntity referenceOrientation) {
        modifyOrientationRelativeTo(referenceOrientation.getLocation());
    }

    default void modifyOrientationRelativeTo(@NotNull Location referenceOrientation) {
        modifyYawOrientation(referenceOrientation.getYaw());
        modifyPitchOrientation(referenceOrientation.getPitch());
    }

    default void modifyYawOrientation(float yaw) {
        getOrientation().setYOrientation(yaw);
    }

    default void modifyPitchOrientation(float pitch) {
        getOrientation().setXOrientation(pitch - 90f);
    }

}
