package com.acrylic.version_1_8.blocks;

import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockData implements MCBlockData {

    private final Material material;
    private final byte data;

    public BlockData(@NotNull Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public BlockData(@NotNull Block block) {
        this.material = block.getType();
        this.data = block.getData();
    }

    @NotNull
    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public byte getData() {
        return data;
    }

    @Override
    public void setAsBlock(@NotNull Block block) {
        block.setType(material);
        block.setData(data);
    }

}
