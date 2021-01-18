package com.acrylic.version_1_8.worldblocksaver;

import com.acrylic.universal.worldblocksaver.SerializedBlockSaveInstance;
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
    private final byte dmg;

    // x, y, z, world, mateiral, dmg
    public BlockSaveSerialized(@NotNull String[] serialized) {
        try {
            this.x = Integer.parseInt(serialized[0]);
            this.y = Integer.parseInt(serialized[1]);
            this.z = Integer.parseInt(serialized[2]);
            this.world = serialized[3];
            this.materialOrdinal = Integer.parseInt(serialized[4]);
            this.dmg = (serialized.length >= 6) ? Byte.parseByte(serialized[5]) : 0;
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("The specified serialized string array, " + Arrays.toString(serialized) + " is not deserializable via " + this.getClass());
        }
    }

    public BlockSaveSerialized(@NotNull Block block) {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.world = block.getWorld().getName();
        this.materialOrdinal = block.getType().ordinal();
        this.dmg = block.getData();
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

    public byte getDmg() {
        return dmg;
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
        if (block != null) {
            block.setType(getMaterial());
            block.setData(dmg);
        }
    }

    @NotNull
    @Override
    public String[] serialize() {
        return (dmg == 0) ? new String[] {
                x + "", y + "", z + "", world, materialOrdinal + ""
        } : new String[] {
                x + "", y + "", z + "", world, materialOrdinal + "", dmg + ""
        };
    }

}
