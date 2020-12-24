package com.acrylic.universal.regions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 0x01 - canBreakBlock
 * 0x02 - canPlaceBlock
 * 0x04 - canReceiveDamage
 * 0x08 - canDealDamage
 * 0x016 - canOpenChest
 */
public class SimpleProtectedRegion
        extends SimpleRegion
        implements PlayerProtected {

    int bitMask = 0;

    public SimpleProtectedRegion(@NotNull Block start, @NotNull Block end) {
        super(start, end);
    }

    public SimpleProtectedRegion(@NotNull Location start, @NotNull Location end) {
        super(start, end);
    }

    @Override
    public boolean canBreakBlock(@NotNull Player player, @NotNull Block block) {
        return false;
    }

    @Override
    public boolean canPlaceBlock(@NotNull Player player, @NotNull Block block) {
        return false;
    }

    @Override
    public boolean canReceiveDamage(@NotNull Player player, @NotNull Entity attacker) {
        return false;
    }

    @Override
    public boolean canDealDamage(@NotNull Player player, @NotNull Entity victim) {
        return false;
    }

    @Override
    public boolean canOpenChest(@NotNull Player player, @NotNull Block block) {
        return false;
    }
}
