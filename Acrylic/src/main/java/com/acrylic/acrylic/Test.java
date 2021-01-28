package com.acrylic.acrylic;

import com.acrylic.universal.Universal;
import com.acrylic.universal.blocks.MCBlockData;
import com.acrylic.universal.worldblocksaver.BlockSavable;
import com.acrylic.universal.worldblocksaver.BlockSaveObserver;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class Test implements BlockSaveObserver {

    List<BlockSave> list = new LinkedList<>();

    public void add(@NotNull Block block) {
        list.add(new BlockSave(block));
        block.setType(Material.BEDROCK);
    }

    @NotNull
    @Override
    public List<BlockSave> getSavableCollection() {
        return list;
    }

    public static class BlockSave implements BlockSavable {

        private final MCBlockData blockData;
        private final Block block;

        public BlockSave(@NotNull Block block) {
            this.block = block;
            blockData = Universal.getAcrylicPlugin().getBlockFactory().getBlockData(block);
        }

        @NotNull
        @Override
        public Location getBlockLocationToSave() {
            return block.getLocation();
        }

        @NotNull
        @Override
        public MCBlockData getSaveAsBlockData() {
            return blockData;
        }

    }

}
