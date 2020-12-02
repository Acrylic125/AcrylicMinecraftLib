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

    private int offsetX = 0, offsetY = 0, offsetZ = 0;
    private int amtX = 1, amtY = 1, amtZ = 1;
    private int sizeX = 0, sizeY = 0, sizeZ = 0;

    private final int originX, originY, originZ;
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
        this.sizeX = sizeX;
        return this;
    }

    public GridBuilder setSizeY(int sizeY) {
        this.sizeY = sizeY;
        return this;
    }

    public GridBuilder setSizeZ(int sizeZ) {
        this.sizeZ = sizeZ;
        return this;
    }

    public GridBuilder setSize(int x, int y, int z) {
        return setSizeX(x).setSizeY(y).setSizeZ(z);
    }

    public void createGrid(@NotNull BiConsumer<Location, Location> action) {
        Vector cursor = new Vector(originX, originY, originZ);
        for (int x = 0; x < amtX; x++) {
            cursor.setX((x * sizeX) + (x * offsetX) + originX);
            for (int y = 0; y < amtY; y++) {
                cursor.setY((y * sizeY) + (y * offsetY) + originY);
                for (int z = 0; z < amtZ; z++) {
                    cursor.setZ((z * sizeZ) + (z * offsetZ) + originZ);
                    Location start = cursor.toLocation(world);
                    Location end = cursor.toLocation(world);
                    end.setX(start.getX() + sizeX);
                    end.setY(start.getY() + sizeY);
                    end.setZ(start.getZ() + sizeZ);
                    action.accept(start, end);
                }
            }
        }
    }

}
