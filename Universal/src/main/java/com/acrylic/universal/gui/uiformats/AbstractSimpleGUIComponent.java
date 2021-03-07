package com.acrylic.universal.gui.uiformats;

import com.acrylic.universal.gui.items.GUIItem;
import com.acrylic.universal.gui.paginated.PageableGUIItemComponent;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedArrayList;
import paginatedcollection.PaginatedCollection;

import java.util.Collection;

import static com.acrylic.universal.gui.InventoryUI.CHEST_COLUMNS_PER_ROW;

public abstract class AbstractSimpleGUIComponent
        implements UIFormat, PageableGUIItemComponent {

    private PaginatedCollection<GUIItem> items;
    private int initialRow;
    private int totalItemsInMenu;
    private int offsetLeft = 0;

    public AbstractSimpleGUIComponent() {
        this(1, 6);
    }

    public AbstractSimpleGUIComponent(@NotNull PaginatedCollection<GUIItem> items) {
        this(items, 1, 6);
    }

    public AbstractSimpleGUIComponent(int initialRow, int lastRow) {
        this(new PaginatedArrayList<>(10), initialRow, lastRow);
    }

    public AbstractSimpleGUIComponent(@NotNull PaginatedCollection<GUIItem> items, int initialRow, int lastRow) {
        this.items = items;
        setTotalItemsInMenu(initialRow, lastRow);
    }

    public abstract static class Builder<T extends AbstractSimpleGUIComponent> {

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

    public void setGUIItems(@NotNull Collection<GUIItem> items) {
        this.items = new PaginatedArrayList<>(this.totalItemsInMenu);
        this.items.setCollection(items);
    }

    public void addGUIItem(@NotNull GUIItem item) {
        getGUIItems().add(item);
    }

    public void removeGUIItem(@NotNull GUIItem item) {
        getGUIItems().remove(item);
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

    @NotNull
    @Override
    public UIFormat getGUIComponent() {
        return this;
    }



}
