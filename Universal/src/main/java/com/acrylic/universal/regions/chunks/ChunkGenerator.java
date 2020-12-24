package com.acrylic.universal.regions.chunks;

import com.acrylic.universal.regions.Region;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ChunkGenerator {

    SimpleChunkGenerator SIMPLE_CHUNK_GENERATOR = new SimpleChunkGenerator();

    int getXSize();

    int getZSize();

    Region getChunkRegion(@NotNull Location location);

    int getChunkHash(@NotNull Location location);

    default int getChunkXComponent(double x) {
        return getComponentHelper(x, getXSize());
    }

    default int getChunkZComponent(double z) {
        return getComponentHelper(z, getZSize());
    }

    int hash(@Nullable World world, double x, double z);

    static int getComponentHelper(double v, int size) {
        return (int) Math.floor(v / size);
    }

}
