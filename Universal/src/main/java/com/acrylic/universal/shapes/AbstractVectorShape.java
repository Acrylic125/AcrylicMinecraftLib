package com.acrylic.universal.shapes;

import com.acrylic.universal.math.Orientation3D;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractVectorShape
        extends AbstractIndefiniteShape {

    private final Orientation3D orientation;
    private Vector reusableVector = null;

    public AbstractVectorShape(float frequency) {
        this(frequency, 0, 0, 0);
    }

    public AbstractVectorShape(float frequency, float xRot, float yRot, float zRot) {
        super(frequency);
        orientation = new Orientation3D(xRot, yRot, zRot);
    }

    public Orientation3D getOrientation() {
        return orientation;
    }

    public Vector getReusableVector() {
        return reusableVector;
    }

    public boolean shouldReuseVector() {
        return reusableVector != null;
    }

    /**
     *
     * @param shouldReuse Should a reusable vector be used as an additive vector.
     *                    If false, a new vector object is created instead.
     *
     *                    It is highly recommended to set this to true as this will
     *                    save memory usage.
     * @return this.
     */
    public AbstractVectorShape setShouldReuse(boolean shouldReuse) {
        this.reusableVector = (shouldReuse) ? new Vector(0, 0, 0) : null;
        return this;
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
    public Location transformPoint(Location location) {
        return transformPoint(location, getIndex());
    }

    @Override
    public Location transformPoint(Location location, int index) {
        return location.add(rotateAdditiveVector(getAdditiveVector(index)));
    }

    public abstract Vector getAdditiveVector(int index);

    public Vector getAdditiveVector() {
        return getAdditiveVector(getIndex());
    }


}
