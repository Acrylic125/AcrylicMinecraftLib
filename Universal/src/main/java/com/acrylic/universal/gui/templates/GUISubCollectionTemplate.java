package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.AbstractInventoryBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * GUISubCollection only supports Chest/Shulker UIs.
 *
 * (3 Rows, 9 Columns)
 * ROWS : COLUMNS ------>
 *  | i i i i i i i i i
 *  | i i i i i i i i i
 *  | i i i i i i i i i
 * \/
 *
 * (3 Rows, 9 Columns, 1 Left Offset (L), 2 Right Offset (R))
 * ROWS : COLUMNS ------>
 *  | L i i i i i i R R
 *  | L i i i i i i R R
 *  | L i i i i i i R R
 * \/
 */
public class GUISubCollectionTemplate extends AbstractGUISubCollectionTemplate {

    @Override
    public void applySubCollection(Inventory inventory, Collection<ItemStack> collection) {

    }
}
