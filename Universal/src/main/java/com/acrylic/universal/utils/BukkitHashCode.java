package com.acrylic.universal.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BukkitHashCode {

    private static int computeHash(double a, int hash) {
        long b = Double.doubleToLongBits(a);
        return (int) (31 * hash + (b ^ b >>> 32));
    }

    /**
     * This is used as an identifier.
     *
     * It is adapted from the {@link Location} hashcode.
     */
    public static int getHashCode(@NotNull Location location) {
        return getHashCode(location, false);
    }

    public static int getHashCode(@NotNull Location location, boolean withWorld) {
        return getHashCode((withWorld) ? location.getWorld() : null, location.getX(), location.getY(), location.getZ());
    }

    public static int getHashCode(@NotNull Block block) {
        return getHashCode(block, false);
    }

    public static int getHashCode(@NotNull Block block, boolean withWorld) {
        return getHashCode((withWorld) ? block.getWorld() : null, block.getX(), block.getY(), block.getZ());
    }

    public static int getHashCode(@Nullable World world, double x, double y, double z) {
        return getHashCode((world == null) ? null : world.getUID(), x, y, z);
    }

    public static int getHashCode(@Nullable UUID worldID, double x, double y, double z) {
        int hash = 3;
        hash = computeHash(x, hash);
        hash = computeHash(y, hash);
        hash = computeHash(z, hash);
        if (worldID != null)
            hash = computeHash(worldID.hashCode(), hash);
        return hash;
    }

}
