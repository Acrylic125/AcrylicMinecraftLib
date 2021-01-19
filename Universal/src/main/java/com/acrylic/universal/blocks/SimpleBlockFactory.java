package com.acrylic.universal.blocks;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockFactory implements BlockFactory {

    @NotNull
    @Override
    public BlockData getBlockData(@NotNull Block block) {
        return new BlockData(block);
    }
}
