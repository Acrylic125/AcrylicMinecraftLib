package com.acrylic.universal.worldblocksaver;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockSaverSerializer<T extends SerializedBlockSaveInstance> {

    @NotNull
    T serialize(@NotNull Block block);

    @Nullable
    T deserialize(@NotNull String[] serialized);

}
