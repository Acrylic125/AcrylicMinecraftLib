package com.acrylic.universal.entity;

import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;
import org.jetbrains.annotations.NotNull;

/**
 * Why is this named old?
 *
 * In newer versions of the Bukkit API, the switch to
 * a proper instance instance of Wither Skeleton was made
 *
 * @see org.bukkit.entity.ZombieVillager
 * @see ZombieVillagerEntityInstance
 *
 * Please use {@link ZombieVillagerEntityInstance} if the
 * {@link org.bukkit.entity.ZombieVillager} instance is
 * available in THAT version of the Bukkit API.
 */
public interface ZombieVillagerEntityInstance extends ZombieEntityInstance {

    void setProfession(@NotNull Villager.Profession profession);

    @NotNull
    @Override
    ZombieVillager getBukkitEntity();

    @Override
    default ZombieType getZombieType() {
        return ZombieType.VILLAGER;
    }
}
