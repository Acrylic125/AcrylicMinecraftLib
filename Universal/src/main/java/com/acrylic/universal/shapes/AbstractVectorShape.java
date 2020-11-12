package com.acrylic.universal.shapes;

import com.acrylic.universal.math.TrigonometrySet;
import com.acrylic.universal.utils.LocationUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractVectorShape extends AbstractShape {

    private TrigonometrySet xRot;
    private TrigonometrySet yRot;
    private TrigonometrySet zRot;

    public AbstractVectorShape(float radius, int amount) {
        this(radius, amount, 0, 0, 0);
    }

    public AbstractVectorShape(float radius, int amount, float xRot, float yRot, float zRot) {
        super(radius, amount);
        setRot(xRot, yRot, zRot);
    }

    public AbstractVectorShape setRot(LivingEntity entity) {
        Location location = entity.getEyeLocation();
        return setRot((float) location.getX() * 360, (float) location.getY() * 360, (float) location.getZ() * 360);
    }

    public void rotate(Location location) {
        LocationUtils.rotateAroundAxisX(location, xRot.getSin(), xRot.getCos());
        LocationUtils.rotateAroundAxisY(location, yRot.getSin(), yRot.getCos());
        LocationUtils.rotateAroundAxisZ(location, zRot.getSin(), zRot.getCos());
    }

    public AbstractVectorShape setRot(float xRot, float yRot, float zRot) {
        return setXRot(xRot).setYRot(yRot).setZRot(zRot);
    }

    public AbstractVectorShape setRot(TrigonometrySet xRot, TrigonometrySet yRot, TrigonometrySet zRot) {
        return setXRot(xRot).setYRot(yRot).setZRot(zRot);
    }

    public AbstractVectorShape setXRot(float xRot) {
        if (this.xRot != null) {
            this.xRot.set(xRot);
            return this;
        }
        return setXRot(new TrigonometrySet(xRot));
    }

    public AbstractVectorShape setXRot(@NotNull TrigonometrySet xRot) {
        this.xRot = xRot;
        return this;
    }

    public AbstractVectorShape setYRot(float yRot) {
        if (this.yRot != null) {
            this.yRot.set(yRot);
            return this;
        }
        return setYRot(new TrigonometrySet(-yRot));
    }

    public AbstractVectorShape setYRot(@NotNull TrigonometrySet yRot) {
        this.yRot = yRot;
        return this;
    }

    public AbstractVectorShape setZRot(float zRot) {
        if (this.zRot != null) {
            this.zRot.set(zRot);
            return this;
        }
        return setZRot(new TrigonometrySet(zRot));
    }

    public AbstractVectorShape setZRot(@NotNull TrigonometrySet zRot) {
        this.zRot = zRot;
        return this;
    }


}
