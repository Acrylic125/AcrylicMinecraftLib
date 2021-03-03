package com.acrylic.universal.ui.components;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUIComponent {

    void applyComponentToInventory(@NotNull Inventory inventory, @Nullable Player player);

}
