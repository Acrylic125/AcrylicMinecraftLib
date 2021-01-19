package com.acrylic.version_1_8.blocks;

import com.acrylic.universal.blocks.BlockFactory;
import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockFactory implements BlockFactory {

    @NotNull
    @Override
    public BlockData getBlockData(@NotNull Block block) {
        return new BlockData(block);
    }

    @Override
    public MCBlockData getBlockData(@NotNull Material material) {
        return getBlockData(material, (byte) 0);
    }

    @Override
    public MCBlockData getBlockData(@NotNull Material material, byte data) {
        return new BlockData(material, data);
    }


}
