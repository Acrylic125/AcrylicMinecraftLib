package com.acrylic.universal.blocks;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockFactory {

    @NotNull
    MCBlockData getBlockData(@NotNull Block block);

}
