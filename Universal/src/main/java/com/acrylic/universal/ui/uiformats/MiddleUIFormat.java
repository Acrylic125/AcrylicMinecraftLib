package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class MiddleUIFormat<T extends GUIItem>
        extends AbstractSimpleUIFormat<T> {

    public MiddleUIFormat() {
    }

    public MiddleUIFormat(@NotNull Collection<T> items) {
        super(items);
    }

    public MiddleUIFormat(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    public MiddleUIFormat(@NotNull Collection<T> items, int initialRow, int lastRow) {
        super(items, initialRow, lastRow);
    }

    @Override
    public void format(@NotNull Inventory inventory, @NotNull Collection<T> collection, @Nullable Player viewer) {
        final int endingIndex = getTotalItemsInMenu(),
                size = collection.size();
        final float divisor = getTotalColumnsPerRow();
        int currentSlot = getStartingSlot();
        short index = 0, rowsElapsed = 0;
        final int rowToCenter = (int) Math.floor(size / divisor),
                centeringOffset = (int) (Math.ceil(divisor / 2) - Math.ceil((size - (rowToCenter * divisor)) / 2f));
        for (T item : collection) {
            if (index > endingIndex)
                break;
            if (rowsElapsed == rowToCenter) {
                currentSlot += centeringOffset;
                rowsElapsed = -1;
            }
            inventory.setItem(currentSlot, item.getItem());
            currentSlot++;
            index++;
            if (index % divisor == 0) {
                rowsElapsed++;
                currentSlot += getTotalOffset();
            }
        }
    }
}
