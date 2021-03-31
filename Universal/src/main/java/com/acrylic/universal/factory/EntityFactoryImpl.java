package com.acrylic.universal.factory;

import com.acrylic.universal.entity.*;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class EntityFactoryImpl implements EntityFactory {
    @Override
    public ArmorStandInstance getNewArmorStandInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public ArmorStandInstance getNewGiantInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public ZombieEntityInstance getNewZombieInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public SkeletonEntityInstance getNewSkeletonInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public WitherSkeletonEntityInstance getNewWitherSkeletonInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public StrayEntityInstance getNewStrayInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public SpiderEntityInstance getNewSpiderInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public CaveSpiderEntityInstance getNewCaveSpiderInstance(@NotNull Location location) {
        return null;
    }

    @Override
    public IronGolemEntityInstance getNewIronGolemInstance(@NotNull Location location) {
        return null;
    }
}
