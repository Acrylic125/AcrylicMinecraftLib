package com.acrylic.universal.worldblocksaver;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockSavable {

    @NotNull
    Block getBlockToSave();

}
