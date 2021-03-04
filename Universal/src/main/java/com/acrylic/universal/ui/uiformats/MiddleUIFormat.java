package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.OpenDetails;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class MiddleUIFormat
        extends AbstractSimpleUIFormat {

    public static Builder builder() {
        return builder(new MiddleUIFormat());
    }

    public static Builder builder(@NotNull MiddleUIFormat uiFormat) {
        return new Builder(uiFormat);
    }

    public static final class Builder extends AbstractSimpleUIFormat.Builder<MiddleUIFormat> {

        private Builder(MiddleUIFormat uiFormat) {
            super(uiFormat);
        }
    }

    public MiddleUIFormat() {
    }

    public MiddleUIFormat(@NotNull Collection<GUIItem> items) {
        super(items);
    }

    public MiddleUIFormat(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    public MiddleUIFormat(@NotNull Collection<GUIItem> items, int initialRow, int lastRow) {
        super(items, initialRow, lastRow);
    }

    @Override
    public void format(@NotNull Inventory inventory, @NotNull Collection<GUIItem> collection, @Nullable Player viewer) {
        final int endingIndex = getTotalItemsInMenu(),
                size = collection.size();
        final float divisor = getTotalColumnsPerRow();
        int currentSlot = getStartingSlot();
        short index = 0, rowsElapsed = 0;
        final OpenDetails openDetails = new OpenDetails(viewer, inventory);
        final int rowToCenter = (int) Math.floor(size / divisor),
                centeringOffset = (int) (Math.ceil(divisor / 2) - Math.ceil((size - (rowToCenter * divisor)) / 2f));
        for (GUIItem item : collection) {
            if (index > endingIndex)
                break;
            if (rowsElapsed == rowToCenter) {
                currentSlot += centeringOffset;
                rowsElapsed = -1;
            }
            inventory.setItem(currentSlot, item.getItem(openDetails));
            currentSlot++;
            index++;
            if (index % divisor == 0) {
                rowsElapsed++;
                currentSlot += getTotalOffset();
            }
        }
    }
}
