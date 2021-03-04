package com.acrylic.universal.regions.chunks;

import com.acrylic.universal.regions.SimpleRegion;
import com.acrylic.universal.utils.keys.ChunkKey;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleChunkGenerator implements ChunkGenerator {

    private int xSize = 100;
    private int zSize = 100;

    public void setXSize(int xSize) {
        this.xSize = xSize;
    }

    @Override
    public int getXSize() {
        return xSize;
    }

    public void setZSize(int zSize) {
        this.zSize = zSize;
    }

    @Override
    public int getZSize() {
        return zSize;
    }

    @Override
    public SimpleRegion getChunkRegion(@NotNull Location location) {
        return null;
    }

    @Override
    public ChunkKey toKey(@NotNull Location location) {
        return toKey(location.getWorld(), getChunkXComponent(location.getX()), getChunkZComponent(location.getZ()));
    }

    @Override
    public ChunkKey toKey(@NotNull World world, int x, int z) {
        return new ChunkKey(world, x, z);
    }
}
