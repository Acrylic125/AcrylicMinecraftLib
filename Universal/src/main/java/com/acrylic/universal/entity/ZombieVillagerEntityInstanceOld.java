package com.acrylic.universal.entity;

import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public interface ZombieVillagerEntityInstanceOld extends ZombieEntityInstance {

    void setProfession(@NotNull Villager.Profession profession);

    @NotNull
    @Override
    Zombie getBukkitEntity();

    @Override
    default ZombieType getZombieType() {
        return ZombieType.VILLAGER;
    }
}
