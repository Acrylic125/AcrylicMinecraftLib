package com.acrylic.universal.shapes;

import com.acrylic.universal.math.Orientation3D;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractVectorShape extends AbstractShape {

    private final Orientation3D orientation;

    public AbstractVectorShape(float radius, int amount) {
        this(radius, amount, 0, 0, 0);
    }

    public AbstractVectorShape(float radius, int amount, float xRot, float yRot, float zRot) {
        super(radius, amount);
        orientation = new Orientation3D(xRot, yRot, zRot);
    }

    public AbstractVectorShape setOrientation(@NotNull Orientation3D orientation) {
        this.orientation.cloneFrom(orientation);
        return this;
    }

    public AbstractVectorShape setOrientation(@NotNull LivingEntity referenceOrientation) {
        return setOrientation(referenceOrientation.getLocation());
    }

    public AbstractVectorShape setOrientation(@NotNull Location referenceOrientation) {
         return setOrientationYaw(referenceOrientation.getYaw())
                 .setOrientationPitch(referenceOrientation.getPitch());
    }

    public AbstractVectorShape setOrientationYaw(float yaw) {
        orientation.setYOrientation(yaw);
        return this;
    }

    public AbstractVectorShape setOrientationPitch(float pitch) {
        orientation.setXOrientation(pitch - 90f);
        return this;
    }

    public Vector rotateAdditiveVector(Vector vector) {
        orientation.transform(vector);
        return vector;
    }

    @Override
    public Location invoke(Location location) {
        return location.add(rotateAdditiveVector(getAdditiveVector()));
    }

    public abstract Vector getAdditiveVector();


}
