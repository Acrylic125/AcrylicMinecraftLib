package com.acrylic.version_1_8.factory;

import com.acrylic.universal.entity.*;
import com.acrylic.universal.entity.impl.*;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.version_1_8.entity.BukkitSkeletonInstanceImpl;
import com.acrylic.version_1_8.entity.BukkitWitherSkeletonInstanceOldImpl;
import com.acrylic.version_1_8.entity.BukkitZombieVillagerOldImpl;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public ArmorStandInstance getNewArmorStandInstance(@NotNull Location location) {
        return new BukkitArmorStandInstance(location);
    }

    @Override
    public GiantEntityInstance getNewGiantInstance(@NotNull Location location) {
        return new BukkitGiantEntityInstance(location);
    }

    @Override
    public ZombieEntityInstance getNewZombieInstance(@NotNull Location location) {
        return new BukkitZombieInstance(location);
    }

    @Override
    public ZombieVillagerEntityInstance getNewZombieVillagerInstance(@NotNull Location location) {
        throw new IllegalStateException("Please use getNewZombieVillagerInstanceOld() instead.");
    }

    @Override
    public ZombieVillagerEntityInstanceOld getNewZombieVillagerInstanceOld(@NotNull Location location) {
        return new BukkitZombieVillagerOldImpl(location);
    }

    @Override
    public SkeletonEntityInstance getNewSkeletonInstance(@NotNull Location location) {
        return new BukkitSkeletonInstanceImpl(location);
    }

    @Override
    public WitherSkeletonEntityInstance getNewWitherSkeletonInstance(@NotNull Location location) {
        throw new IllegalStateException("Please use getNewWitherSkeletonInstanceOld() instead.");
    }

    @Override
    public WitherSkeletonEntityInstanceOld getNewWitherSkeletonInstanceOld(@NotNull Location location) {
        return new BukkitWitherSkeletonInstanceOldImpl(location);
    }

    @Override
    public StrayEntityInstance getNewStrayInstance(@NotNull Location location) {
        throw new IllegalStateException("Stray is not a valid entity type in 1.8.");
    }

    @Override
    public SpiderEntityInstance getNewSpiderInstance(@NotNull Location location) {
        return new BukkitSpiderInstance(location);
    }

    @Override
    public CaveSpiderEntityInstance getNewCaveSpiderInstance(@NotNull Location location) {
        return new BukkitCaveSpiderInstance(location);
    }

    @Override
    public IronGolemEntityInstance getNewIronGolemInstance(@NotNull Location location) {
        return new BukkitIronGolemInstance(location);
    }
}
