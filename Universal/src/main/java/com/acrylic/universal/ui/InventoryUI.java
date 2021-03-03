package com.acrylic.universal.ui;

import com.acrylic.universal.items.AbstractItemBuilder;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface InventoryUI<T extends ItemStack> {

    int CHEST_COLUMNS_PER_ROW = 9;

    @NotNull
    Map<Integer, T> getItems();

    InventoryUI<T> title(@Nullable String title);

    InventoryUI<T> rows(int rows);

    InventoryUI<T> inventoryType(@Nullable InventoryType inventoryType);

    InventoryUI<T> setItems(@NotNull Map<Integer, T> items);

    InventoryUI<T> addItem(int slot, @NotNull AbstractItemBuilder itemBuilder);

    InventoryUI<T> addItem(int slot, @Nullable ItemStack item);

    InventoryUI<T> removeItem(int slot);

    InventoryUI<T> clearItems();

    @NotNull
    Inventory build();

}
