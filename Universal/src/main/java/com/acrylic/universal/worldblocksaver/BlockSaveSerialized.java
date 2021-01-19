package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.Universal;
import com.acrylic.universal.blocks.MCBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class BlockSaveSerialized implements SerializedBlockSaveInstance {

    private final String world;
    private final int x;
    private final int y;
    private final int z;
    private final int materialOrdinal;
    private final byte data;

    // x, y, z, world, mateiral
    public BlockSaveSerialized(@NotNull String[] serialized) {
        try {
            this.x = Integer.parseInt(serialized[0]);
            this.y = Integer.parseInt(serialized[1]);
            this.z = Integer.parseInt(serialized[2]);
            this.world = serialized[3];
            this.materialOrdinal = Integer.parseInt(serialized[4]);
            this.data = (serialized.length >= 6) ? Byte.parseByte(serialized[5]) : 0;
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("The specified serialized string array, " + Arrays.toString(serialized) + " is not deserializable via " + this.getClass());
        }
    }

    public BlockSaveSerialized(@NotNull Block block) {
        this(block, Universal.getAcrylicPlugin().getBlockFactory().getBlockData(block));
    }

    public BlockSaveSerialized(@NotNull Block block, @NotNull MCBlockData originalData) {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = block.getWorld().getName();
        this.materialOrdinal = originalData.getMaterial().ordinal();
        Bukkit.broadcastMessage(materialOrdinal + "");
        this.data = originalData.getData();
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

    @Nullable
    @Override
    public Block getBlock() {
        World world = getWorld();
        if (world != null) {
            return world.getBlockAt(x, y, z);
        }
        return null;
    }

    @Override
    public void restore() {
        Block block = getBlock();
        if (block != null)
            block.setType(getMaterial());
    }

    @NotNull
    @Override
    public String[] serialize() {
        return (data != 0) ? new String[] {
                x + "", y + "", z + "", world, materialOrdinal + ""
        } : new String[] {
                x + "", y + "", z + "", world, materialOrdinal + "", data + ""
        };
    }
}
