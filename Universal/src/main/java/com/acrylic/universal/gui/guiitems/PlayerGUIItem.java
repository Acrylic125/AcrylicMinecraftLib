package com.acrylic.universal.gui.guiitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface PlayerGUIItem extends AbstractGUIItem {

    void applyTo(@NotNull final Inventory inventory, @NotNull final Player viewer);

}
