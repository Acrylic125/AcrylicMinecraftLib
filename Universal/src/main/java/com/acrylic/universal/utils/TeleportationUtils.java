package com.acrylic.universal.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Deprecated
public class TeleportationUtils {

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

    public static void tp(Entity entity, Location location) {
        try {
            setPositionRotation.invoke(getHandle.invoke(entity), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException ex) {
            entity.teleport(location);
        }
    }

    public static void tp(Entity entity, double x, double y, double z, float yaw, float pitch) {
        try {
            setPositionRotation.invoke(getHandle.invoke(entity), x, y, z, yaw, pitch);
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException ex) {
            entity.teleport(new Location(entity.getWorld(), x, y, z, yaw, pitch));
        }
    }

    public static void tp(Entity entity, double x, double y, double z) {
        tp(entity, x, y, z, 0, 0);
    }

    public static void tp(Entity entity, float yaw, float pitch) {
        Location location = entity.getLocation();
        tp(entity, location.getX(), location.getY(), location.getZ(), yaw, pitch);
    }

}
