package com.acrylic.universal.regions;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

@Getter
public final class GridBuilder {

    private int offsetX = 0;
    private int offsetY = 0;
    private int offsetZ = 0;
    private int amtX = 1;
    private int amtY = 1;
    private int amtZ = 1;
    private int sizeX = 0;
    private int sizeY = 0;
    private int sizeZ = 0;

    private final int originX;
    private final int originY;
    private final int originZ;
    private final World world;

    public static GridBuilder create(@NotNull Location origin) {
        return new GridBuilder(origin);
    }

    public static GridBuilder create(@NotNull Block origin) {
        return new GridBuilder(origin);
    }

    public GridBuilder(@NotNull Location origin) {
        this.originX = origin.getBlockX();
        this.originY = origin.getBlockY();
        this.originZ = origin.getBlockZ();
        this.world = origin.getWorld();
    }

    public GridBuilder(@NotNull Block origin) {
        this(origin.getLocation());
    }

    public GridBuilder setOffsetX(int offsetX) {
        this.offsetX = Math.max(offsetX, 0);
        return this;
    }

    public GridBuilder setOffsetY(int offsetY) {
        this.offsetY = Math.max(offsetY, 0);
        return this;
    }

    public GridBuilder setOffsetZ(int offsetZ) {
        this.offsetZ = Math.max(offsetZ, 0);
        return this;
    }

    public GridBuilder setOffset(int offsetX, int offsetY, int offsetZ) {
        return setOffsetX(offsetX).setOffsetY(offsetY).setOffsetZ(offsetZ);
    }

    public GridBuilder setAmtX(int amtX) {
        this.amtX = Math.max(amtX, 1);
        return this;
    }

    public GridBuilder setAmtY(int amtY) {
        this.amtY = Math.max(amtY, 1);
        return this;
    }

    public GridBuilder setAmtZ(int amtZ) {
        this.amtZ = Math.max(amtZ, 1);
        return this;
    }

    public GridBuilder setAmt(int x, int y, int z) {
        return setAmtX(x).setAmtY(y).setAmtZ(z);
    }

    public GridBuilder setSizeX(int sizeX) {
        this.sizeX = Math.max(sizeX, 1);
        return this;
    }

    public GridBuilder setSizeY(int sizeY) {
        this.sizeY = Math.max(sizeY, 1);
        return this;
    }

    public GridBuilder setSizeZ(int sizeZ) {
        this.sizeZ = Math.max(sizeZ, 1);
        return this;
    }

    public GridBuilder setSize(int x, int y, int z) {
        return setSizeX(x).setSizeY(y).setSizeZ(z);
    }

    public void createGrid(@NotNull BiConsumer<Location, Location> action) {
        Vector cursor = new Vector(originX, originY, originZ);
        for (int x = 0; x < amtX; x++) {
            cursor.setX((x * sizeX) + (x * offsetX));
            for (int y = 0; y < amtY; y++) {
                cursor.setX((y * sizeY) + (y * offsetY));
                for (int z = 0; z < amtZ; z++) {
                    cursor.setZ((z * sizeZ) + (z * offsetZ));
                    Location start = cursor.toLocation(world);
                    Location end = cursor
                            .setX(start.getX() + sizeX)
                            .setY(start.getY() + sizeY)
                            .setZ(start.getZ() + sizeZ)
                            .toLocation(world);
                    action.accept(start, end);
                }
            }
        }
    }

}
