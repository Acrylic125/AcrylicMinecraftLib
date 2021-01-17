package com.acrylic.universal.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LocationUtils {

    private static Method getHandle;
    private static Method setPositionRotation;

    static {
        try {
            getHandle = Class.forName(Bukkit.getServer().getClass().getPackage().getName() + ".entity.CraftEntity").getDeclaredMethod("getHandle");
            setPositionRotation = getHandle.getReturnType().getDeclaredMethod("setPositionRotation", double.class, double.class, double.class, float.class, float.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static float getFixedYaw(Entity entity) {
        return getFixedYaw(entity.getLocation());
    }

    public static float getFixedYaw(Location location) {
        return getFixedYaw(location.getYaw());
    }

    /**
     *
     * @param yaw Bukkit yaw
     * @return Returns the correct yaw from N=0
     */
    public static float getFixedYaw(float yaw) {
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
        return location.add(0.5f, 0.5f, 0.5f);
    }

    public static void teleport(Entity entity, Location location) {
        try {
            setPositionRotation.invoke(getHandle.invoke(entity), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException ex) {
            entity.teleport(location);
        }
    }

    public static void teleport(Entity entity, double x, double y, double z, float yaw, float pitch) {
        try {
            setPositionRotation.invoke(getHandle.invoke(entity), x, y, z, yaw, pitch);
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException ex) {
            entity.teleport(new Location(entity.getWorld(), x, y, z, yaw, pitch));
        }
    }

    public static void teleport(Entity entity, double x, double y, double z) {
        teleport(entity, x, y, z, 0, 0);
    }

    public static void teleport(Entity entity, float yaw, float pitch) {
        Location location = entity.getLocation();
        teleport(entity, location.getX(), location.getY(), location.getZ(), yaw, pitch);
    }

}
