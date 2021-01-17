package com.acrylic.universal.worldblocksaver;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SerializedBlockSaveInstance {

    @NotNull
    String getWorldName();

    @Nullable
    default World getWorld() {
        return Bukkit.getWorld(getWorldName());
    }

    int getX();

    int getY();

    int getZ();

    @NotNull
    Material getMaterial();

    byte getBlockData();

    @Nullable
    Block getBlock();

    void restore();

}
