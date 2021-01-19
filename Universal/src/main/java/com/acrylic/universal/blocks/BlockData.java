package com.acrylic.universal.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockData implements MCBlockData {

    private final int material;

    public BlockData(@NotNull Material material) {
        this.material = material.ordinal();
    }

    public BlockData(@NotNull Block block) {
        this.material = block.getType().ordinal();
    }

    @Override
    public int getMaterialOrdinal() {
        return material;
    }

    @Override
    public byte getData() {
        return 0;
    }

}
