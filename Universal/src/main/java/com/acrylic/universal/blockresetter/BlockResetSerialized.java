package com.acrylic.universal.blockresetter;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class BlockResetSerialized implements SerializedBlockResetInstance {

    private final String world;
    private final int x;
    private final int y;
    private final int z;
    private final int materialOrdinal;
    private final byte blockDamage;

    // x, y, z, world, mateiral, dmg
    public BlockResetSerialized(@NotNull String... serialized) {
        try {
            this.x = Integer.parseInt(serialized[0]);
            this.y = Integer.parseInt(serialized[1]);
            this.z = Integer.parseInt(serialized[2]);
            this.world = serialized[3];
            this.materialOrdinal = Integer.parseInt(serialized[4]);
            this.blockDamage = Byte.parseByte(serialized[5]);
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("The specified serialized string array, " + Arrays.toString(serialized) + " is not deserializable via " + this.getClass());
        }
    }

    public BlockResetSerialized(@NotNull Block block) {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = block.getWorld().getName();
        this.materialOrdinal = block.getType().ordinal();
        this.blockDamage = block.getData();
    }

    @NotNull
    @Override
    public String getWorldName() {
        return world;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @NotNull
    @Override
    public Material getMaterial() {
        return Material.values()[materialOrdinal];
    }

    @Override
    public byte getBlockData() {
        return blockDamage;
    }

    @Nullable
    @Override
    public Block getBlock() {
        World world = getWorld();
        if (world != null) {
            return world.getBlockAt(x, y, z);
        }
        return null;
    }
}
