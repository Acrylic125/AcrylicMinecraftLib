package com.acrylic.version_1_8.blocks;

import com.acrylic.universal.blocks.BlockFactory;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockFactory implements BlockFactory {

    @NotNull
    @Override
    public BlockData getBlockData(@NotNull Block block) {
        return new BlockData(block);
    }
}
