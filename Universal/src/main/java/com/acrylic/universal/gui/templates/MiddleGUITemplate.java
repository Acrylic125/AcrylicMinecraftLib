package com.acrylic.universal.gui.templates;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Refer to {@link GUISubCollectionTemplate} for more information.
 *
 * (3 Rows, 9 Columns, 16 Items)
 * ROWS : COLUMNS ------>
 *  | i i i i i i i i i
 *  | O i i i i i i i O
 *  | O O O O O O O O O
 * \/
 *
 * (3 Rows, 9 Columns, 1 Left Offset (L), 2 Right Offset (R), 14 Items)
 * ROWS : COLUMNS ------>
 *  | L i i i i i i R R
 *  | L i i i i i i R R
 *  | L O O i i O O R R
 * \/
 */
public class MiddleGUITemplate extends AbstractGUISubCollectionTemplate {

    public MiddleGUITemplate() {
        super();
    }

    public MiddleGUITemplate(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    @Override
    public void applySubCollection(@NotNull Inventory inventory, @NotNull Collection<ItemStack> collection) {
        final int endingIndex = getTotalItemsInMenu();
        final int size = collection.size();
        int currentSlot = getStartingSlot();
        float divisor = getTotalColumnsPerRow();
        short index = 0;
        short rowsElapsed = 0;
        final short rowToCenter = (short) Math.floor(size / divisor);
        final short centeringOffset = (short) (Math.floor(divisor / 2) - Math.ceil((size - (rowToCenter * divisor)) / 2f));
        for (ItemStack itemStack : collection) {
            if (index > endingIndex)
                break;
            if (rowsElapsed == rowToCenter) {
                currentSlot += centeringOffset;
                rowsElapsed = -1;
             }
            inventory.setItem(currentSlot, itemStack);
            currentSlot++;
            index++;
            if (index % divisor == 0) {
                rowsElapsed++;
                currentSlot += getTotalOffset();
            }
        }
    }
}
