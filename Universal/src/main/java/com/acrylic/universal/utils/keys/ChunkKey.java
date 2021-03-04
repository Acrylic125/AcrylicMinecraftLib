package com.acrylic.universal.utils.keys;

import com.acrylic.universal.utils.BukkitHashCode;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class ChunkKey {

    private final UUID world;
    private final int x, z;

    public ChunkKey(@NotNull Chunk chunk) {
        this(Objects.requireNonNull(chunk.getWorld()), chunk.getX(), chunk.getZ());
    }

    public ChunkKey(@NotNull Block block) {
        this(block.getChunk());
    }

    public ChunkKey(@NotNull Location location) {
        this(location.getChunk());
    }

    public ChunkKey(@NotNull World world, int x, int z) {
        this.world = world.getUID();
        this.x = x;
        this.z = z;
    }

    @Nullable
    public World getWorld() {
        return Bukkit.getWorld(world);
    }

    @NotNull
    public UUID getWorldID() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof ChunkKey && ((ChunkKey) obj).world == world;
    }

    @Override
    public int hashCode() {
        return BukkitHashCode.getHashCode(world, x, z);
    }
}
