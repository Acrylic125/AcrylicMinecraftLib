package com.acrylic.universal.regions;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PlayerProtected {

    boolean canBreakBlock(@NotNull Player player, @NotNull Block block);

    boolean canPlaceBlock(@NotNull Player player, @NotNull Block block);

    boolean canReceiveDamage(@NotNull Player player, @NotNull Entity attacker);

    boolean canDealDamage(@NotNull Player player, @NotNull Entity victim);

    boolean canOpenChest(@NotNull Player player, @NotNull Block block);

}
