package com.acrylic.universal.gui.templates;

import org.bukkit.Bukkit;
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

    public GUISubCollectionTemplate() {
        super();
    }

    public GUISubCollectionTemplate(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    @Override
    public void applySubCollection(@NotNull final Inventory inventory, @NotNull final Collection<ItemStack> collection) {
        final int endingIndex = getTotalItemsInMenu();
        final float divisor = getTotalColumnsPerRow();
        int currentSlot = getStartingSlot();
        short index = 0;
        for (ItemStack itemStack : collection) {
            if (index > endingIndex)
                break;
            inventory.setItem(currentSlot, itemStack);
            currentSlot++;
            index++;
            if (index % divisor == 0)
                currentSlot += getTotalOffset();
        }
    }
}
