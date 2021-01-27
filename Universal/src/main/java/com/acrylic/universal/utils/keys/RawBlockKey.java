package com.acrylic.universal.utils.keys;

import com.acrylic.universal.utils.BukkitHashCode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RawBlockKey {

    private final int x;
    private final int y;
    private final int z;

    public RawBlockKey(@NotNull Location location) {
        this((int) Math.floor(location.getX() - 0.5), (int) Math.floor(location.getY() - 0.5), (int) Math.floor(location.getZ() - 0.5));
    }

    public RawBlockKey(@NotNull Block block) {
        this(block.getX(), block.getY(), block.getZ());
    }

    public RawBlockKey(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RawBlockKey))
            return false;
        RawBlockKey location = ((RawBlockKey) obj);
        return location.x == x && location.y == y && location.z == z;
    }

    @Override
    public int hashCode() {
        return BukkitHashCode.getHashCode((UUID) null, x, y, z);
    }

}
