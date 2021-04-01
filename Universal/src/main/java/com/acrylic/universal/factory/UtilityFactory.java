package com.acrylic.universal.factory;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface UtilityFactory {

    MCBlockData getBlockData(@NotNull Block block);

    MCBlockData getBlockData(@NotNull Material material);

    MCBlockData getBlockData(@NotNull Material material, byte data);

}
