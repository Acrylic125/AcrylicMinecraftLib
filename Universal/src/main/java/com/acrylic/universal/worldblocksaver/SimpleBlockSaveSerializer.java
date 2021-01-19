package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleBlockSaveSerializer implements BlockSaverSerializer<SerializedBlockSaveInstance> {

    @NotNull
    @Override
    public SerializedBlockSaveInstance serialize(@NotNull Block block) {
        return new BlockSaveSerialized(block);
    }

    @NotNull
    @Override
    public SerializedBlockSaveInstance serialize(@NotNull Block block, @NotNull MCBlockData saveAs) {
        return new BlockSaveSerialized(block, saveAs);
    }

    @NotNull
    @Override
    public SerializedBlockSaveInstance serialize(@NotNull BlockSavable blockSavable) {
        return serialize(blockSavable.getBlockToSave(), blockSavable.getSaveAsBlockData());
    }

    @Nullable
    @Override
    public SerializedBlockSaveInstance deserialize(@NotNull String[] serialized) {
        return new BlockSaveSerialized(serialized);
    }
}
