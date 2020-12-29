package com.acrylic.universal.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemGiveAction {

    void accept(@NotNull ItemStack item, @NotNull Player receiver, boolean hasEnoughSpace, boolean isProtected, long protectedDuration);

}
