package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockSavable {

    @NotNull
    Block getBlockToSave();

    @NotNull
    MCBlockData getSaveAsBlockData();

}
