package com.acrylic.version_1_8.entity;

import com.acrylic.universal.entity.ZombieVillagerEntityInstanceOld;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public class BukkitZombieVillagerOldImpl
        extends BukkitZombieInstanceImpl
        implements ZombieVillagerEntityInstanceOld {

    public BukkitZombieVillagerOldImpl(@NotNull Location location) {
        super(location, ZombieType.VILLAGER);
    }

    public BukkitZombieVillagerOldImpl(@NotNull Zombie zombie) {
        super(zombie);
    }

    @Override
    public void setProfession(@NotNull Villager.Profession profession) {
        throw new IllegalStateException("Cannot set the profession.");
    }
}
