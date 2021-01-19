package com.acrylic.version_1_8.blocks;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockData implements MCBlockData {

    private final int material;
    private final byte data;

    public BlockData(@NotNull Material material, byte data) {
        this.material = material.ordinal();
        this.data = data;
    }

    public BlockData(@NotNull Block block) {
        this.material = block.getType().ordinal();
        this.data = block.getData();
    }

    @Override
    public int getMaterialOrdinal() {
        return material;
    }

    @Override
    public byte getData() {
        return data;
    }

}
