package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockSavable {

    @NotNull
    Location getBlockLocationToSave();

    @NotNull
    MCBlockData getSaveAsBlockData();

    @NotNull
    default Block getBlockToSave() {
        return getBlockLocationToSave().getBlock();
    }

}
