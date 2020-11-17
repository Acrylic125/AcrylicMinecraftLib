package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.AbstractInventoryBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

@Setter @Getter
public abstract class AbstractGUISubCollectionTemplate extends AbstractGUITemplate {

    private final ArrayList<ItemStack> subCollection = new ArrayList<>();
    private int initialRow;
    private int lastRow;
    private int offsetLeft = 0;
    private int offsetRight = 0;

    public AbstractGUISubCollectionTemplate() {
        this(1, 6);
    }

    public AbstractGUISubCollectionTemplate(int initialRow, int lastRow) {
        this.initialRow = initialRow;
        this.lastRow = lastRow;
    }

    public int getTotalOffset() {
        return offsetLeft + offsetRight;
    }

    public int getTotalRows() {
        return lastRow - initialRow + 1;
    }

    public int getTotalItemsInMenu() {
        return (AbstractInventoryBuilder.CHEST_COLUMNS_PER_ROW - getTotalOffset()) * getTotalRows();
    }

    public ArrayList<ItemStack> getSubCollection() {
        return subCollection;
    }

    public void apply(@NotNull Inventory inventory, Collection<ItemStack> collection) {
        final int size = inventory.getSize();
        final int maxSize = getTotalItemsInMenu();
        assert size <= maxSize : "The inventory does not have enough space!";
        super.apply(inventory);
        applySubCollection(inventory, collection);
    }

    @Override
    public void apply(@NotNull Inventory inventory) {
        apply(inventory, subCollection);
    }

    public abstract void applySubCollection(Inventory inventory, Collection<ItemStack> collection);

}
