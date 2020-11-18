package com.acrylic.universal.gui.templates;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Refer to {@link GUISubCollectionTemplate} for more information.
 *
 * (3 Rows, 9 Columns, 14 Items)
 * ROWS : COLUMNS ------>
 *  | i O i O i O i O i
 *  | O i O i O i O i O
 *  | i O i O i O i O i
 * \/
 *
 * (3 Rows, 9 Columns, 1 Left Offset (L), 2 Right Offset (R), 7 Items)
 * ROWS : COLUMNS ------>
 *  | L i O i O i O R R
 *  | L O i O i O i R R
 *  | L i O O O O O R R
 * \/
 */
@Setter @Getter
public class AlternateGUITemplate extends AbstractGUISubCollectionTemplate {

    private int alternating;

    public AlternateGUITemplate(int alternating) {
        super();
        this.alternating = alternating;
    }

    public AlternateGUITemplate(int alternating, int initialRow, int lastRow) {
        super(initialRow, lastRow);
        this.alternating = alternating;
    }

    @Override
    public int getTotalItemsInMenu() {
        return super.getTotalItemsInMenu() / 2;
    }

    @Override
    public void applySubCollection(@NotNull Inventory inventory, @NotNull Collection<ItemStack> collection) {
        final int endingIndex = getTotalItemsInMenu();
        int currentSlot = getStartingSlot();
        float divisor = (getTotalColumnsPerRow() / (float) alternating);
        short index = 0;
        for (ItemStack itemStack : collection) {
            if (index > endingIndex)
                break;
            inventory.setItem((int) (currentSlot + (getTotalOffset() * Math.floor(index / divisor))), itemStack);
            currentSlot += alternating;
            for (int i = 0; i < alternating; i++) {
                index++;
                if (index % divisor == 0)
                    currentSlot += getTotalOffset();
            }
        }
    }
}
