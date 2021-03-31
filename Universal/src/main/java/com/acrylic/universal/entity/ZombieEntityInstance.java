package com.acrylic.universal.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface ZombieEntityInstance extends LivingEntityInstance {

    @NotNull
    @Override
    Zombie getBukkitEntity();

    boolean isHusk();

}
