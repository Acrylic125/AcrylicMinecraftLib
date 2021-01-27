package com.acrylic.universal.utils.keys;

import com.acrylic.universal.utils.BukkitHashCode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class LocationKey extends RawLocationKey {

    private final UUID world;

    public LocationKey(@NotNull Location location) {
        super(location);
        this.world = Objects.requireNonNull(location.getWorld()).getUID();
    }

    public LocationKey(@NotNull World world, double x, double y, double z) {
        super(x, y, z);
        this.world = world.getUID();
    }

    @Nullable
    public World getWorld() {
        return Bukkit.getWorld(world);
    }

    @NotNull
    public UUID getWorldID() {
        return world;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof LocationKey && ((LocationKey) obj).world == world;
    }

    @Override
    public int hashCode() {
        return BukkitHashCode.getHashCode(world, getX(), getY(), getZ());
    }
}
