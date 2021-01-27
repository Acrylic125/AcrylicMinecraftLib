package com.acrylic.universal.utils.keys;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class FullLocationKey extends LocationKey {

    private final float yaw;
    private final float pitch;

    public FullLocationKey(@NotNull Location location) {
        super(location);
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public FullLocationKey(@NotNull World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public FullLocationKey(@NotNull World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z);
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof FullLocationKey) {
            FullLocationKey fullLocationKey = (FullLocationKey) obj;
            return fullLocationKey.yaw == yaw && fullLocationKey.pitch == pitch;
        }
        return false;
    }
}
