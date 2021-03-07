package com.acrylic.universal.entity;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface BukkitEntityInstance extends EntityInstance {

    @Override
    default void setName(String name) {
        getBukkitEntity().setCustomName(ChatUtils.get(name));
    }

    @Override
    default void setNameVisible(boolean nameVisible) {
        getBukkitEntity().setCustomNameVisible(nameVisible);
    }

    @Override
    default void teleport(@NotNull Location location) {
        getBukkitEntity().teleport(location);
    }

    @Override
    default void setVelocity(@NotNull Vector velocity) {
        getBukkitEntity().setVelocity(velocity);
    }
}
