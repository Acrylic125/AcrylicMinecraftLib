package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.SpiderEntityInstance;
import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitSpiderInstance extends BukkitLivingEntityInstance implements SpiderEntityInstance {

    private final Spider spider;

    public BukkitSpiderInstance(@NotNull Location location) {
        this((Spider) spawnEntity(location, EntityType.SPIDER));
    }

    public BukkitSpiderInstance(@NotNull Spider spider) {
        this.spider = spider;
    }

    @NotNull
    @Override
    public Spider getBukkitEntity() {
        return spider;
    }
}
