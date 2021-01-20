package com.acrylic.universal.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface MCBlockData {

    default int getMaterialOrdinal() {
        return getMaterial().ordinal();
    }

    @NotNull
    Material getMaterial();

    byte getData();

    void setAsBlock(@NotNull Block block);

}
