package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.SpiderEntityInstance;
import com.acrylic.universal.entity.ZombieEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitSpiderInstance extends BukkitLivingEntityInstance implements SpiderEntityInstance {

    private final Spider spider;
    private final SpiderType spiderType;

    public BukkitSpiderInstance(@NotNull Location location) {
        this(location, SpiderType.NORMAL);
    }

    public BukkitSpiderInstance(@NotNull Location location, @NotNull SpiderType spiderType) {
        this.spider = spawnEntity(location, EntityType.SPIDER);
        this.spiderType = spiderType;
    }

    public BukkitSpiderInstance(@NotNull Spider spider) {
        this.spider = spider;
        if (spider instanceof CaveSpider) {
            spiderType = SpiderType.CAVE_SPIDER;
        } else {
            spiderType = SpiderType.NORMAL;
        }
    }

    @NotNull
    @Override
    public Spider getBukkitEntity() {
        return spider;
    }

    @Override
    public SpiderType getSpiderType() {
        return spiderType;
    }
}
