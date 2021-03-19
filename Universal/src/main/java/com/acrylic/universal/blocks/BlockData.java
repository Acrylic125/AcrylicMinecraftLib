package com.acrylic.universal.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.jetbrains.annotations.NotNull;

public class BlockData implements MCBlockData {

    private final Material material;

    public BlockData(@NotNull Material material) {
        this.material = material;
    }

    public BlockData(@NotNull Block block) {
        this.material = block.getType();
    }

    @NotNull
    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public byte getData() {
        return 0;
    }

    @Override
    public void setAsBlock(@NotNull Block block) {
        block.setType(material);
    }

}
