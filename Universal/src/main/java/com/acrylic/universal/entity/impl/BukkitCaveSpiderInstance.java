package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.CaveSpiderEntityInstance;
import com.acrylic.universal.entity.SpiderEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitCaveSpiderInstance extends BukkitSpiderInstance implements CaveSpiderEntityInstance {

    public BukkitCaveSpiderInstance(@NotNull Location location) {
        super(location, SpiderType.CAVE_SPIDER);
    }

    public BukkitCaveSpiderInstance(@NotNull CaveSpider caveSpider) {
        super(caveSpider);
    }

    @NotNull
    @Override
    public CaveSpider getBukkitEntity() {
        return (CaveSpider) super.getBukkitEntity();
    }
}
