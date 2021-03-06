package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.items.GUIItem;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedCollection;

import java.util.Collection;

public class MiddleGUIComponent
        extends AbstractSimpleGUIComponent {

    public static Builder builder() {
        return builder(new MiddleGUIComponent());
    }

    public static Builder builder(@NotNull MiddleGUIComponent uiFormat) {
        return new Builder(uiFormat);
    }

    public static final class Builder extends AbstractSimpleGUIComponent.Builder<MiddleGUIComponent> {

        private Builder(MiddleGUIComponent uiFormat) {
            super(uiFormat);
        }
    }

    public MiddleGUIComponent() {
    }

    public MiddleGUIComponent(@NotNull PaginatedCollection<GUIItem> items) {
        super(items);
    }

    public MiddleGUIComponent(int initialRow, int lastRow) {
        super(initialRow, lastRow);
    }

    public MiddleGUIComponent(@NotNull PaginatedCollection<GUIItem> items, int initialRow, int lastRow) {
        super(items, initialRow, lastRow);
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        applyPageToInventory(inventoryDetails, 1);
    }

    @Override
    public void formatPageToInventory(@NotNull InventoryDetails inventoryDetails, @NotNull Collection<GUIItem> itemsToFormatWith, int page) {
        final int endingIndex = getTotalItemsInMenu(),
                size = itemsToFormatWith.size();
        final float divisor = getTotalColumnsPerRow();
        final Inventory inventory = inventoryDetails.getInventory();
        int currentSlot = getStartingSlot();
        short index = 0, rowsElapsed = 0;
        final int rowToCenter = (int) Math.floor(size / divisor),
                centeringOffset = (int) (Math.ceil(divisor / 2) - Math.ceil((size - (rowToCenter * divisor)) / 2f));
        for (GUIItem item : itemsToFormatWith) {
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

}
