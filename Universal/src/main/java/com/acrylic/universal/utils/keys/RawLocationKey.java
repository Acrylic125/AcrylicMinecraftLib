package com.acrylic.universal.utils.keys;

import com.acrylic.universal.utils.BukkitHashCode;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RawLocationKey {

    private final double x;
    private final double y;
    private final double z;

    public RawLocationKey(@NotNull Location location) {
        this(location.getX(), location.getY(), location.getZ());
    }

    public RawLocationKey(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RawLocationKey))
            return false;
        RawLocationKey location = ((RawLocationKey) obj);
        return location.x == x && location.y == y && location.z == z;
    }

    @Override
    public int hashCode() {
        return BukkitHashCode.getHashCode((UUID)  null, x, y, z);
    }
}
