package com.acrylic.universal.blocks;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public interface MCBlockData {

    int getMaterialOrdinal();

    @NotNull
    default Material getMaterial() {
        return Material.values()[getMaterialOrdinal()];
    }

    byte getData();

}
