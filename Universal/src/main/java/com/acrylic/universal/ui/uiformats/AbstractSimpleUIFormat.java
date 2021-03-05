package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.paginated.PageableUIFormat;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedArrayList;
import paginatedcollection.PaginatedCollection;

import java.util.ArrayList;
import java.util.Collection;

import static com.acrylic.universal.ui.InventoryUI.CHEST_COLUMNS_PER_ROW;

public abstract class AbstractSimpleUIFormat
        implements UIFormat, PageableUIFormat {

    private PaginatedCollection<GUIItem> items;
    private int initialRow;
    private int totalItemsInMenu;
    private int offsetLeft = 0;

    public AbstractSimpleUIFormat() {
        this(1, 6);
    }

    public AbstractSimpleUIFormat(@NotNull PaginatedCollection<GUIItem> items) {
        this(items, 1, 6);
    }

    public AbstractSimpleUIFormat(int initialRow, int lastRow) {
        this(new PaginatedArrayList<>(10), initialRow, lastRow);
    }

    public AbstractSimpleUIFormat(@NotNull PaginatedCollection<GUIItem> items, int initialRow, int lastRow) {
        this.items = items;
        setTotalItemsInMenu(initialRow, lastRow);
    }

    public abstract static class Builder<T extends AbstractSimpleUIFormat> {

        private final T uiFormat;

        protected Builder(T uiFormat) {
            this.uiFormat = uiFormat;
        }

        public Builder<T> initialRow(int initialRow) {
            uiFormat.setInitialRow(initialRow);
            return this;
        }

        public Builder<T> offsetLeft(int offset) {
            uiFormat.setOffsetLeft(offset);
            return this;
        }

        public Builder<T> offsetRight(int offset) {
            uiFormat.setOffsetRight(offset);
            return this;
        }

        public Builder<T> totalItemsInMenu(int total) {
            uiFormat.setTotalItemsInMenu(total);
            return this;
        }

        public Builder<T> setGUIItems(@NotNull Collection<GUIItem> items) {
            uiFormat.setGUIItems(items);
            return this;
        }

        public Builder<T> addGUIItem(@NotNull GUIItem guiItem) {
            uiFormat.addGUIItem(guiItem);
            return this;
        }

        public Builder<T> removeGUIItem(@NotNull GUIItem guiItem) {
            uiFormat.removeGUIItem(guiItem);
            return this;
        }

        public Builder<T> clear() {
            uiFormat.clear();
            return this;
        }

        public T build() {
            return uiFormat;
        }
    }

    public int getInitialRow() {
        return initialRow;
    }

    public void setInitialRow(int initialRow) {
        this.initialRow = initialRow;
    }

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public void setOffsetLeft(int offsetLeft) {
        this.offsetLeft = offsetLeft;
    }

    public int getOffsetRight() {
        return offsetRight;
    }

    public void setOffsetRight(int offsetRight) {
        this.offsetRight = offsetRight;
    }

    private int offsetRight = 0;

    public int getStartingSlot() {
        return getOffsetLeft() + (CHEST_COLUMNS_PER_ROW * (getInitialRow() - 1));
    }

    public int getTotalOffset() {
        return offsetLeft + offsetRight;
    }

    public int getTotalColumnsPerRow() {
        return CHEST_COLUMNS_PER_ROW - getTotalOffset();
    }

    public void setTotalItemsInMenu(int totalItemsInMenu) {
        this.totalItemsInMenu = totalItemsInMenu;
        this.items.setMaxElementsPerPage(totalItemsInMenu);
    }

    public void setTotalItemsInMenu(int initialRow, int lastRow) {
        assert initialRow <= lastRow : "The initial row must be smaller than the last row.";
        assert initialRow > 0 : "The rows specified must be greater than 0.";
        this.initialRow = initialRow;
        setTotalItemsInMenu((lastRow - initialRow + 1) * getTotalColumnsPerRow());
    }

    public int getTotalItemsInMenu() {
        return totalItemsInMenu;
    }

    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails, @NotNull PaginatedCollection<GUIItem> collection) {
        Inventory inventory = inventoryDetails.getInventory();
        final int size = inventory.getSize(),
                maxSize = getTotalItemsInMenu();
        assert size <= maxSize : "The inventory does not have enough space!";
        format(inventoryDetails, collection);
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        applyComponentToInventory(inventoryDetails, items);
    }

    @Override
    public void setGUIItems(@NotNull Collection<GUIItem> items) {
        this.items = new PaginatedArrayList<>(this.totalItemsInMenu);
        this.items.setCollection(items);
    }

    @NotNull
    @Override
    public Collection<GUIItem> getGUIItems() {
        return items;
    }

    public void setGUIItems(@NotNull PaginatedCollection<GUIItem> items) {
        this.items = items;
    }

    @NotNull
    @Override
    public PaginatedCollection<GUIItem> getAllPageableItems() {
        return this.items;
    }

    public abstract void format(@NotNull InventoryDetails inventoryDetails, @NotNull PaginatedCollection<GUIItem> collection);

}
