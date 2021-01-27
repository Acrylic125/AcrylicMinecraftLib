package com.acrylic.universal.utils.keys;

import com.acrylic.universal.utils.BukkitHashCode;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BlockKey extends RawBlockKey {

    private final UUID world;

    public BlockKey(@NotNull Location location) {
        super(location);
        this.world = location.getWorld().getUID();
    }

    public BlockKey(@NotNull Block block) {
        super(block);
        this.world = block.getWorld().getUID();
    }

    public BlockKey(@NotNull World world, int x, int y, int z) {
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
        return super.equals(obj) && obj instanceof BlockKey && ((BlockKey) obj).world == world;
    }

    @Override
    public int hashCode() {
        return BukkitHashCode.getHashCode(world, getX(), getY(), getZ());
    }
}
