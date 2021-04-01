package com.acrylic.universal.entity.impl;

import com.acrylic.universal.entity.ZombieVillagerEntityInstance;
import org.bukkit.Location;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;

public class BukkitZombieVillagerInstance
        extends BukkitZombieInstance
        implements ZombieVillagerEntityInstance {

    public BukkitZombieVillagerInstance(@NotNull Location location) {
        super(location, ZombieType.VILLAGER);
    }

    public BukkitZombieVillagerInstance(@NotNull ZombieVillager zombie) {
        super(zombie);
    }

    @Override
    public void setProfession(@NotNull Villager.Profession profession) {
        getBukkitEntity().setVillagerProfession(profession);
    }

    @NotNull
    @Override
    public ZombieVillager getBukkitEntity() {
        return (ZombieVillager) super.getBukkitEntity();
    }

}
