package com.acrylic.universal.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

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
        if (yaw < 0.0D)
            yaw += 360.0D;
        yaw -= 90;
        return yaw;
    }

    public static boolean isLocationInCuboid(Location toCheck, Location loc1, Location loc2) {
        return isNumberInBetween(toCheck.getX(), loc1.getX(), loc2.getX()) &&
                isNumberInBetween(toCheck.getY(), loc1.getY(), loc2.getY()) &&
                isNumberInBetween(toCheck.getZ(), loc1.getZ(), loc2.getZ());
    }

    private static boolean isNumberInBetween(double toCheck, double a, double b) {
        return ((Math.abs(a - toCheck) + Math.abs(b - toCheck)) <= Math.abs(a - b));
    }

    public static Location convertToFixedBlockLocation(@NotNull Location location) {
        return location.add(-0.5, 0, -0.5);
    }

}
