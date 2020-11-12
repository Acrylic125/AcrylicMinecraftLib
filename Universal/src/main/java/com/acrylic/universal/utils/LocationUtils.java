package com.acrylic.universal.utils;

import com.google.common.base.Preconditions;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class LocationUtils {

    @NotNull
    public Vector rotateAroundNonUnitAxis(Vector a, @NotNull Vector axis, double angle) throws IllegalArgumentException {
        Preconditions.checkArgument(axis != null, "The provided axis vector was null");

        double x = a.getX(), y = a.getY(), z = a.getZ();
        double x2 = axis.getX(), y2 = axis.getY(), z2 = axis.getZ();

        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);
        double dotProduct = a.dot(axis);

        double xPrime = x2 * dotProduct * (1d - cosTheta)
                + x * cosTheta
                + (-z2 * y + y2 * z) * sinTheta;
        double yPrime = y2 * dotProduct * (1d - cosTheta)
                + y * cosTheta
                + (z2 * x - x2 * z) * sinTheta;
        double zPrime = z2 * dotProduct * (1d - cosTheta)
                + z * cosTheta
                + (-y2 * x + x2 * y) * sinTheta;

        return a.setX(xPrime).setY(yPrime).setZ(zPrime);
    }

    public float getFixedYaw(Entity entity) {
        return getFixedYaw(entity.getLocation());
    }

    public float getFixedYaw(Location location) {
        return getFixedYaw(location.getYaw());
    }

    /**
     *
     * @param yaw Bukkit yaw
     * @return Returns the correct yaw from N=0
     */
    public float getFixedYaw(float yaw) {
        yaw = (yaw - 90.0F) % 360.0F;
        if (yaw < 0.0D) {
            yaw += 360.0D;
        }
        yaw -= 90;
        return yaw;
    }

    public Location rotateAroundAxisX(Location location, double cos, double sin) {
        double y = location.getY() * cos - location.getZ() * sin;
        double z = location.getY() * sin + location.getZ() * cos;
        location.setY(y);
        location.setZ(z);
        return location;
    }

    public Location rotateAroundAxisY(Location location, double cos, double sin) {
        double x = location.getX() * cos + location.getZ() * sin;
        double z = location.getX() * -sin + location.getZ() * cos;
        location.setX(x);
        location.setZ(z);
        return location;
    }

    public Location rotateAroundAxisZ(Location location, double cos, double sin) {
        double x = location.getX() * cos - location.getY() * sin;
        double y = location.getX() * sin + location.getY() * cos;
        location.setX(x);
        location.setY(y);
        return location;
    }

}
