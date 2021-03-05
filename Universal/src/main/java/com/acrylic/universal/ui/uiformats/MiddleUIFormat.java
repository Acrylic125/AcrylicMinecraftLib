package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.items.GUIItem;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedCollection;

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

    public MiddleUIFormat(@NotNull PaginatedCollection<GUIItem> items) {
        super(items);
    }

    public MiddleUIFormat(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    public MiddleUIFormat(@NotNull PaginatedCollection<GUIItem> items, int initialRow, int lastRow) {
        super(items, initialRow, lastRow);
    }

    @Override
    public void formatPageByCollection(@NotNull InventoryDetails inventoryDetails, @NotNull Collection<GUIItem> itemsToFormatInPage) {
        final int endingIndex = getTotalItemsInMenu(),
                size = itemsToFormatInPage.size();
        final float divisor = getTotalColumnsPerRow();
        final Inventory inventory = inventoryDetails.getInventory();
        int currentSlot = getStartingSlot();
        short index = 0, rowsElapsed = 0;
        final int rowToCenter = (int) Math.floor(size / divisor),
                centeringOffset = (int) (Math.ceil(divisor / 2) - Math.ceil((size - (rowToCenter * divisor)) / 2f));
        for (GUIItem item : itemsToFormatInPage) {
            if (index > endingIndex)
                break;
            if (rowsElapsed == rowToCenter) {
                currentSlot += centeringOffset;
                rowsElapsed = -1;
            }
            inventory.setItem(currentSlot, item.getItem(inventoryDetails));
            currentSlot++;
            index++;
            if (index % divisor == 0) {
                rowsElapsed++;
                currentSlot += getTotalOffset();
            }
        }
    }

    @Override
    public void format(@NotNull InventoryDetails inventoryDetails, @NotNull PaginatedCollection<GUIItem> collection) {
        formatPage(inventoryDetails, collection);
    }



}
