package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockSaverSerializer<T extends SerializedBlockSaveInstance> {

    @NotNull
    T serialize(@NotNull Block block);

    @NotNull
    T serialize(@NotNull Block block, @NotNull MCBlockData originalBlockData);

    @NotNull
    T serialize(@NotNull Location location);

    @NotNull
    T serialize(@NotNull Location location, @NotNull MCBlockData originalBlockData);

    @NotNull
    T serialize(@NotNull BlockSavable saveAs);

    @Nullable
    T deserialize(@NotNull String[] serialized);

}
