package com.acrylic.universal.entity;

import org.bukkit.entity.Spider;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface SpiderEntityInstance extends LivingEntityInstance {

    @NotNull
    @Override
    Spider getBukkitEntity();

    default boolean isCaveSpider() {
        return false;
    }

}
