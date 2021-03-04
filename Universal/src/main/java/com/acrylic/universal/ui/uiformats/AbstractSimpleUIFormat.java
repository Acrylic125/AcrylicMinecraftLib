package com.acrylic.universal.ui.uiformats;

import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

import static com.acrylic.universal.ui.InventoryUI.CHEST_COLUMNS_PER_ROW;

public abstract class AbstractSimpleUIFormat
        implements UIFormat {

    private Collection<GUIItem> items;
    private int initialRow;
    private int totalItemsInMenu;
    private int offsetLeft = 0;

    public AbstractSimpleUIFormat() {
        this(1, 6);
    }

    public AbstractSimpleUIFormat(@NotNull Collection<GUIItem> items) {
        this(items, 1, 6);
    }

    public AbstractSimpleUIFormat(int initialRow, int lastRow) {
        this(new ArrayList<>(), initialRow, lastRow);
    }

    public AbstractSimpleUIFormat(@NotNull Collection<GUIItem> items, int initialRow, int lastRow) {
        setTotalItemsInMenu(initialRow, lastRow);
        this.items = items;
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
    }

    public void setTotalItemsInMenu(int initialRow, int lastRow) {
        assert initialRow <= lastRow : "The initial row must be smaller than the last row.";
        assert initialRow > 0 : "The rows specified must be greater than 0.";
        this.initialRow = initialRow;
        this.totalItemsInMenu = (lastRow - initialRow + 1) * getTotalColumnsPerRow();
    }

    public int getTotalItemsInMenu() {
        return totalItemsInMenu;
    }

    public void applyComponentToInventory(@NotNull Inventory inventory, @NotNull Collection<GUIItem> collection, @Nullable Player viewer) {
        final int size = inventory.getSize(),
                maxSize = getTotalItemsInMenu();
        assert size <= maxSize : "The inventory does not have enough space!";
        format(inventory, collection, viewer);
    }

    public void applyComponentToInventory(@NotNull Inventory inventory, @NotNull Collection<GUIItem> collection) {
        applyComponentToInventory(inventory, collection, null);
    }

    @Override
    public void applyComponentToInventory(@NotNull Inventory inventory, @Nullable Player player) {
        applyComponentToInventory(inventory, items);
    }

    @NotNull
    @Override
    public Collection<GUIItem> getGUIItems() {
        return items;
    }

    public void setGUIItems(@NotNull Collection<GUIItem> items) {
        this.items = items;
    }


}
