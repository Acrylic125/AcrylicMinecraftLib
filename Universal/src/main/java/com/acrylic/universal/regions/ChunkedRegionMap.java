package com.acrylic.universal.regions;

import com.acrylic.universal.regions.chunks.ChunkGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class ChunkedRegionMap<T extends Region>
        implements RegionMap<T> {

    private final Map<UUID, T> regionMap = new HashMap<>();
    private final Map<Integer, List<T>> chunkRegionIDsMap = new HashMap<>();
    private ChunkGenerator chunkGenerator = ChunkGenerator.SIMPLE_CHUNK_GENERATOR   ;

    @NotNull
    public ChunkGenerator getChunkGenerator() {
        return chunkGenerator;
    }

    public void setChunkGenerator(@NotNull ChunkGenerator chunkGenerator) {
        this.chunkGenerator = chunkGenerator;
    }

    @NotNull
    @Override
    public Map<UUID, T> getRegionMap() {
        return regionMap;
    }

    public Map<Integer, List<T>> getChunkRegionIDsMap() {
        return chunkRegionIDsMap;
    }

    @Nullable
    @Override
    public T getFirstRegion(@NotNull Location location) {
        List<T> regions = chunkRegionIDsMap.get(chunkGenerator.getChunkHash(location));
        return (regions == null) ? null : RegionMap.super.getFirstRegion(location, regions);
    }

    @Nullable
    @Override
    public List<T> getRegions(@NotNull Location location) {
        List<T> regions = chunkRegionIDsMap.get(chunkGenerator.getChunkHash(location));
        return (regions == null) ? new ArrayList<>() : RegionMap.super.getRegions(location, regions);
    }

    @Override
    public void iterateRegions(@NotNull Location location, @NotNull Consumer<T> regionAction) {
        List<T> regions = chunkRegionIDsMap.get(chunkGenerator.getChunkHash(location));
        if (regions != null)
            RegionMap.super.iterateRegions(location, regions, regionAction);
    }

    @Override
    public void addRegion(@NotNull T region) {
        RegionMap.super.addRegion(region);
        mapRegion(region);
    }

    private void mapRegion(T region) {
        Location start = region.getLocation1(), end = region.getLocation2();
        World world = start.getWorld();
        int x1 = chunkGenerator.getChunkXComponent(start.getX()), z1 = chunkGenerator.getChunkZComponent(start.getZ());
        int x2 = chunkGenerator.getChunkXComponent(end.getX()), z2 = chunkGenerator.getChunkZComponent(end.getZ());
        int xMin = Math.min(x1, x2), xMax = Math.max(x1, x2);
        int zMin = Math.min(z1, z2), zMax = Math.max(z1, z2);
        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                int hash = chunkGenerator.hash(world, x, z);
                List<T> ids = chunkRegionIDsMap.get(hash);
                if (ids == null)
                    ids = new ArrayList<>();
                ids.add(region);
                chunkRegionIDsMap.put(hash, ids);
            }
        }
    }
}
