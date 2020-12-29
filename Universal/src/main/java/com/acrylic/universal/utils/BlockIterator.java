package com.acrylic.universal.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class BlockIterator {

    private int x = 0;
    private int y = 0;
    private int z = 0;
    private int x2 = 0;
    private int y2 = 0;
    private int z2 = 0;

    public BlockIterator(int radius) {
        setDimensions(radius, radius, radius);
    }

    public BlockIterator(int x, int y, int z) {
        setDimensions(x, y, z);
    }

    public BlockIterator(int x, int y, int z, int x2, int y2, int z2) {
        setDimensions1(x, y, z).setDimensions2(x2, y2, z2);
    }

    public BlockIterator setDimensionX1(int x) {
        this.x = x;
        return this;
    }

    public BlockIterator setDimensionX2(int x) {
        this.x2 = x;
        return this;
    }

    public BlockIterator setDimensionY1(int y) {
        this.y = y;
        return this;
    }

    public BlockIterator setDimensionY2(int y) {
        this.y2 = y;
        return this;
    }

    public BlockIterator setDimensionZ1(int z) {
        this.z = z;
        return this;
    }

    public BlockIterator setDimensionZ2(int z) {
        this.z2 = z;
        return this;
    }

    public BlockIterator setDimensions(int x, int y, int z) {
        return setDimensions1(x, y, z).setDimensions2(x, y, z);
    }

    public BlockIterator setDimensions1(int x, int y, int z) {
        return setDimensionX1(x).setDimensionY1(y).setDimensionZ1(z);
    }

    public BlockIterator setDimensions2(int x, int y, int z) {
        return setDimensionX2(x).setDimensionY2(y).setDimensionZ2(z);
    }

    public void iterate(@NotNull final Location location, @NotNull final Consumer<Location> action) {
        final double bX = location.getX();
        final double bY = location.getY();
        final double bZ = location.getZ();
        final Location cursor = new Location(location.getWorld(), bX, bY, bZ, location.getYaw(), location.getPitch());
        for (int x = -this.x; x <= this.x2; x++) {
            cursor.setX(bX + x);
            for (int y = -this.y; y <= this.y2; y++) {
                cursor.setY(bY + y);
                for (int z = -this.z; z <= this.z2; z++) {
                    cursor.setZ(bZ + z);
                    action.accept(cursor);
                }
            }
        }
    }

    public void iterateWithObjectCreation(@NotNull final Location location, @NotNull final Consumer<Location> action) {
        iterate(location, loc -> action.accept(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch())));
    }

    public void iterateBlocks(@NotNull final Location location, @NotNull final Consumer<Block> action) {
        iterate(location, loc -> action.accept(loc.getBlock()));
    }

}
