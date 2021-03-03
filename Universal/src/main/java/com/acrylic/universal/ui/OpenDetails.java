package com.acrylic.universal.ui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class OpenDetails {

    private final Player viewer;
    private final Inventory inventory;

    public OpenDetails(@Nullable Player viewer, @NotNull Inventory inventory) {
        this.viewer = viewer;
        this.inventory = inventory;
    }

    @Nullable
    public Player getViewer() {
        return viewer;
    }

    @NotNull
    public Inventory getInventory() {
        return inventory;
    }
}
