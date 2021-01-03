package com.acrylic.universal.utils;

import com.acrylic.universal.items.ItemUtils;
import math.ProbabilityKt;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class SurfaceLocationSearcher {

    private double minSearchX, minSearchZ;
    private double maxSearchX, maxSearchZ;
    private int minYLimit, maxYLimit;
    private Predicate<Location> condition;
    private int searchTries = 5;
    private boolean ignoreWater = false;

    public SurfaceLocationSearcher() {
        this(0, 0);
    }

    public SurfaceLocationSearcher(double min, double max) {
        this(min, max, 0, 256);
    }

    public SurfaceLocationSearcher(double min, double max, int minYLimit, int maxYLimit) {
        this(min, minYLimit, min, max, maxYLimit, max);
    }

    public SurfaceLocationSearcher(double minSearchX, int minYLimit, double minSearchZ, double maxSearchX, int maxYLimit, double maxSearchZ) {
        this.minSearchX = minSearchX;
        this.minYLimit = minYLimit;
        this.minSearchZ = minSearchZ;
        this.maxSearchX = maxSearchX;
        this.maxYLimit = maxYLimit;
        this.maxSearchZ = maxSearchZ;
    }

    private void validateXZValue(double v) {
        if (v < 0)
            throw new IllegalArgumentException("The value specified is not valid. You may only specify positive values.");
    }

    private void validateYValue() {
        if (minYLimit > maxYLimit)
            throw new IllegalArgumentException("The minimum Y limit must be smaller than the maximum Y value.");
    }

    public double getMinSearchX() {
        return minSearchX;
    }

    public SurfaceLocationSearcher setMinSearchX(double minSearchX) {
        validateXZValue(minSearchX);
        this.minSearchX = minSearchX;
        return this;
    }

    public double getMinSearchZ() {
        return minSearchZ;
    }

    public SurfaceLocationSearcher setMinSearchZ(double minSearchZ) {
        validateXZValue(minSearchZ);
        this.minSearchZ = minSearchZ;
        return this;
    }

    public double getMaxSearchX() {
        return maxSearchX;
    }

    public SurfaceLocationSearcher setMaxSearchX(double maxSearchX) {
        validateXZValue(maxSearchX);
        this.maxSearchX = maxSearchX;
        return this;
    }

    public double getMaxSearchZ() {
        return maxSearchZ;
    }

    public SurfaceLocationSearcher setMaxSearchZ(double maxSearchZ) {
        validateXZValue(maxSearchZ);
        this.maxSearchZ = maxSearchZ;
        return this;
    }

    @Nullable
    public Predicate<Location> getCondition() {
        return condition;
    }

    public SurfaceLocationSearcher setCondition(@Nullable Predicate<Location> condition) {
        this.condition = condition;
        return this;
    }

    public int getMinYLimit() {
        return minYLimit;
    }

    public SurfaceLocationSearcher setMinYLimit(int minYLimit) {
        this.minYLimit = minYLimit;
        validateYValue();
        return this;
    }

    public int getMaxYLimit() {
        return maxYLimit;
    }

    public SurfaceLocationSearcher setMaxYLimit(int maxYLimit) {
        this.maxYLimit = maxYLimit;
        validateYValue();
        return this;
    }

    public int getSearchTries() {
        return searchTries;
    }

    public SurfaceLocationSearcher setSearchTries(int searchTries) {
        this.searchTries = searchTries;
        return this;
    }

    public boolean isIgnoreWater() {
        return ignoreWater;
    }

    public SurfaceLocationSearcher setIgnoreWater(boolean ignoreWater) {
        this.ignoreWater = ignoreWater;
        return this;
    }

    @Nullable
    public Location getLocation(@NotNull Location source) {
        World world = source.getWorld();
        return (world == null) ? null : getLocation(world, source, searchTries);
    }

    private Location getLocation(@NotNull World world, @NotNull Location source, int attempt) {
        if (attempt <= 0)
            return null;
        attempt--;
        double x = source.getX() + ProbabilityKt.getPositiveOrNegative(ProbabilityKt.getRandom(minSearchX, maxSearchX)),
                z = source.getZ() + ProbabilityKt.getPositiveOrNegative(ProbabilityKt.getRandom(minSearchZ, maxSearchZ));
        Location loc = new Location(world, x, 0, z);
        loc.setY(getHighestY(loc));
        if (condition != null && !condition.test(loc))
            return getLocation(world, source, attempt);
        return loc;
    }

    public int getHighestY(@NotNull Block block) {
        return getHighestY(block, minYLimit, maxYLimit, ignoreWater);
    }

    public int getHighestY(@NotNull Location location) {
        return getHighestY(location, minYLimit, maxYLimit, ignoreWater);
    }

    public static int getHighestY(@NotNull Block block, int minY, int maxY, boolean ignoreWater) {
        return getHighestY(block.getLocation(), minY, maxY, ignoreWater);
    }

    public static int getHighestY(@NotNull Location location, int minY, int maxY, boolean ignoreWater) {
        Location clone = location.clone();
        for (int i = maxY; i >= minY; i--) {
            clone.setY(i);
            Material material = clone.getBlock().getType();
            if (!(ItemUtils.isAir(material) || (ignoreWater && ItemUtils.isLiquid(material))))
                return i + 1;
        }
        return minY;
    }


}
