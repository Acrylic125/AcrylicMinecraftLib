package com.acrylic.universal.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class InventoryDetails {

    private final Player viewer;
    private final Inventory inventory;

    public InventoryDetails(@Nullable Player viewer, @NotNull Inventory inventory) {
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
