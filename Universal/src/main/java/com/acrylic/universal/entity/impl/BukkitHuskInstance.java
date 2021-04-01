package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.HuskEntityInstance;
import com.acrylic.universal.entity.ZombieVillagerEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;

import static com.acrylic.universal.entity.EntityInstance.spawnEntity;

public class BukkitHuskInstance
        extends BukkitZombieInstance
        implements HuskEntityInstance {

    public BukkitHuskInstance(@NotNull Location location) {
        super(location, ZombieType.HUSK);
    }

    public BukkitHuskInstance(@NotNull Husk zombie) {
        super(zombie);
    }

    @NotNull
    @Override
    public Husk getBukkitEntity() {
        return (Husk) super.getBukkitEntity();
    }

}
