package com.acrylic.universal.factory;

import com.acrylic.universal.blocks.BlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class UtilityFactoryImpl implements UtilityFactory {

    @NotNull
    @Override
    public BlockData getBlockData(@NotNull Block block) {
        return new BlockData(block);
    }

    @Override
    public BlockData getBlockData(@NotNull Material material) {
        return new BlockData(material);
    }

    @Override
    public BlockData getBlockData(@NotNull Material material, byte data) {
        return getBlockData(material);
    }
}
