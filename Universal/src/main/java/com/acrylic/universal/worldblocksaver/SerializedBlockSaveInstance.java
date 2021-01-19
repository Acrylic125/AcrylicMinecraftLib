package com.acrylic.universal.worldblocksaver;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SerializedBlockSaveInstance {

    @NotNull
    String getWorldName();

    @Nullable
    default World getWorld() {
        String name = getWorldName();
        World world = Bukkit.getWorld(name);
        if (world != null)
            return world;
        return Bukkit.createWorld(new WorldCreator(name));
    }

    int getX();

    int getY();

    int getZ();

    @NotNull
    Material getMaterial();

    @Nullable
    Block getBlock();

    void restore();

    @NotNull
    String[] serialize();

}
