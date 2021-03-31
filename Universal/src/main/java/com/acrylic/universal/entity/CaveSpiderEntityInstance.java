package com.acrylic.universal.entity;

import org.bukkit.entity.CaveSpider;
import org.jetbrains.annotations.NotNull;

public interface CaveSpiderEntityInstance extends SpiderEntityInstance {

    @NotNull
    @Override
    CaveSpider getBukkitEntity();

    default boolean isCaveSpider() {
        return true;
    }
}
