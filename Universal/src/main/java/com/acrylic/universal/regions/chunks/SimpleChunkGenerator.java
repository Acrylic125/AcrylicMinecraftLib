package com.acrylic.universal.regions.chunks;

import com.acrylic.universal.regions.SimpleRegion;
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
    public int getChunkHash(@NotNull Location location) {
        return hash(location.getWorld(), getChunkXComponent(location.getX()), getChunkZComponent(location.getZ()));
    }

    @Override
    public int hash(@Nullable World world, double x, double z) {
        int hash = 3;
        hash = 19 * hash + ((world == null) ? 0 : world.hashCode());
        hash = 19 * hash + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return hash;
    }
}
