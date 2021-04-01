package com.acrylic.universal.factory;

import com.acrylic.universal.entity.*;
import com.acrylic.universal.entity.impl.*;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class EntityFactoryImpl implements EntityFactory {
    @Override
    public ArmorStandInstance getNewArmorStandInstance(@NotNull Location location) {
        return new BukkitArmorStandInstance(location);
    }

    @Override
    public ArmorStandInstance getNewGiantInstance(@NotNull Location location) {
        return new BukkitArmorStandInstance(location);
    }

    @Override
    public ZombieEntityInstance getNewZombieInstance(@NotNull Location location) {
        return new BukkitZombieInstance(location);
    }

    @Override
    public ZombieVillagerEntityInstance getNewZombieVillagerInstance(@NotNull Location location) {
        return new BukkitZombieVillagerInstance(location);
    }

    @Override
    public ZombieVillagerEntityInstanceOld getNewZombieVillagerInstanceOld(@NotNull Location location) {
        throw new IllegalStateException("Please use getNewZombieVillagerInstance() instead.");
    }

    @Override
    public SkeletonEntityInstance getNewSkeletonInstance(@NotNull Location location) {
        return new BukkitSkeletonInstance(location);
    }

    @Override
    public WitherSkeletonEntityInstance getNewWitherSkeletonInstance(@NotNull Location location) {
        return new BukkitWitherSkeletonInstance(location);
    }

    @Override
    public WitherSkeletonEntityInstanceOld getNewWitherSkeletonInstanceOld(@NotNull Location location) {
        throw new IllegalStateException("Please use getNewWitherSkeletonInstance() instead.");
    }

    @Override
    public StrayEntityInstance getNewStrayInstance(@NotNull Location location) {
        return new BukkitStrayInstance(location);
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
