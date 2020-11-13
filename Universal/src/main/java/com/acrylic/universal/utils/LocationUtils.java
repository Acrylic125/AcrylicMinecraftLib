package com.acrylic.universal.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

@UtilityClass
public class LocationUtils {

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



    public void rotateAroundAxisX3D(Vector vector, double cos, double sin) {
        double y = vector.getY() * cos - vector.getZ() * sin;
        double z = vector.getY() * sin + vector.getZ() * cos;
        vector.setY(y);
        vector.setZ(z);
    }

    public void rotateAroundAxisY3D(Vector vector, double cos, double sin) {
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;
        vector.setX(x);
        vector.setZ(z);
    }

    public void rotateAroundAxisZ3D(Vector vector, double cos, double sin) {
        double x = vector.getX() * cos - vector.getY() * sin;
        double y = vector.getX() * sin + vector.getY() * cos;
        vector.setX(x);
        vector.setY(y);
    }

}
