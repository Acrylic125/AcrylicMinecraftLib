package com.acrylic.universal.entity;

import org.bukkit.entity.Husk;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface HuskEntityInstance extends ZombieEntityInstance {

    @NotNull
    @Override
    Husk getBukkitEntity();

    @Override
    default boolean isHusk() {
        return true;
    }
}
