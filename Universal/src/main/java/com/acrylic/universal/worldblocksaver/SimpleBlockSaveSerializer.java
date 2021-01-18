package com.acrylic.universal.worldblocksaver;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleBlockSaveSerializer implements BlockSaverSerializer<SerializedBlockSaveInstance> {

    @NotNull
    @Override
    public SerializedBlockSaveInstance serialize(@NotNull Block block) {
        return new BlockSaveSerialized(block);
    }

    @Nullable
    @Override
    public SerializedBlockSaveInstance deserialize(@NotNull String[] serialized) {
        return new BlockSaveSerialized(serialized);
    }
}
