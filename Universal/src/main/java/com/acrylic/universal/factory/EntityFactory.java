package com.acrylic.universal.factory;

import com.acrylic.universal.entity.*;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface EntityFactory {

    ArmorStandInstance getNewArmorStandInstance(@NotNull Location location);

    ArmorStandInstance getNewGiantInstance(@NotNull Location location);

    ZombieEntityInstance getNewZombieInstance(@NotNull Location location);

    ZombieVillagerEntityInstance getNewZombieVillagerInstance(@NotNull Location location);

    ZombieVillagerEntityInstanceOld getNewZombieVillagerInstanceOld(@NotNull Location location);

    SkeletonEntityInstance getNewSkeletonInstance(@NotNull Location location);

    WitherSkeletonEntityInstance getNewWitherSkeletonInstance(@NotNull Location location);

    WitherSkeletonEntityInstanceOld getNewWitherSkeletonInstanceOld(@NotNull Location location);

    StrayEntityInstance getNewStrayInstance(@NotNull Location location);

    SpiderEntityInstance getNewSpiderInstance(@NotNull Location location);

    CaveSpiderEntityInstance getNewCaveSpiderInstance(@NotNull Location location);

    IronGolemEntityInstance getNewIronGolemInstance(@NotNull Location location);

}
