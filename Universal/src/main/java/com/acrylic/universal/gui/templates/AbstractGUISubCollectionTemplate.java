package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.AbstractInventoryBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

@Setter @Getter
public abstract class AbstractGUISubCollectionTemplate extends AbstractGUITemplate {

    private final ArrayList<ItemStack> subCollection = new ArrayList<>();
    private int initialRow;
    private int totalItemsInMenu;
    private int offsetLeft = 0;
    private int offsetRight = 0;

    public AbstractGUISubCollectionTemplate() {
        this(1, 6);
    }

    public AbstractGUISubCollectionTemplate(int initialRow, int lastRow) {
        setTotalItemsInMenu(initialRow, lastRow);
    }

    public int getStartingSlot() {
        return getOffsetLeft() + (9 * (getInitialRow() - 1));
    }

    public void add(ItemStack item) {
        subCollection.add(item);
    }

    public void setTotalItemsInMenu(int initialRow, int lastRow) {
        assert initialRow <= lastRow : "The initial row must be smaller than the last row.";
        assert initialRow > 0 : "The rows specified must be greater than 0.";
        this.initialRow = initialRow;
        this.totalItemsInMenu = (lastRow - initialRow + 1) * getTotalColumnsPerRow();
    }

    public int getTotalOffset() {
        return offsetLeft + offsetRight;
    }

    public int getTotalColumnsPerRow() {
        return AbstractInventoryBuilder.CHEST_COLUMNS_PER_ROW - getTotalOffset();
    }

    public int getTotalItemsInMenu() {
        return totalItemsInMenu;
    }

    public ArrayList<ItemStack> getSubCollection() {
        return subCollection;
    }

    public void apply(@NotNull Inventory inventory, @NotNull Collection<ItemStack> collection, Player viewer) {
        final int size = inventory.getSize();
        final int maxSize = getTotalItemsInMenu();
        assert size <= maxSize : "The inventory does not have enough space!";
        super.apply(inventory, viewer);
        applySubCollection(inventory, collection);
    }

    public void apply(@NotNull Inventory inventory, @NotNull Collection<ItemStack> collection) {
        apply(inventory, collection, null);
    }

    @Override
    public void apply(@NotNull final Inventory inventory) {
        apply(inventory, subCollection);
    }

    public abstract void applySubCollection(@NotNull final Inventory inventory, @NotNull final Collection<ItemStack> collection);

}
