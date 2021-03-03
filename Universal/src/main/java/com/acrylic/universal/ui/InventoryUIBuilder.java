package com.acrylic.universal.ui;

import com.acrylic.universal.items.AbstractItemBuilder;
import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class InventoryUIBuilder implements InventoryUI<ItemStack> {

    private Map<Integer, ItemStack> items;
    private String title;
    private InventoryType inventoryType;
    private int rows = 1;

    public InventoryUIBuilder() {
        this(new HashMap<>());
    }

    public InventoryUIBuilder(@NotNull Map<Integer, ItemStack> items) {
        this.items = items;
    }

    @NotNull
    @Override
    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    @Override
    public InventoryUIBuilder title(@Nullable String title) {
        this.title = title;
        return this;
    }

    @Override
    public InventoryUIBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public InventoryUIBuilder inventoryType(@Nullable InventoryType inventoryType) {
        this.inventoryType = inventoryType;
        return this;
    }

    @Override
    public InventoryUIBuilder setItems(@NotNull Map<Integer, ItemStack> items) {
        this.items = items;
        return this;
    }

    @Override
    public InventoryUIBuilder addItem(int slot, @NotNull AbstractItemBuilder itemBuilder) {
        return addItem(slot, itemBuilder.build());
    }

    @Override
    public InventoryUIBuilder addItem(int slot, @Nullable ItemStack item) {
        this.items.put(slot, item);
        return this;
    }

    @Override
    public InventoryUIBuilder removeItem(int slot) {
        items.remove(slot);
        return this;
    }

    @Override
    public InventoryUIBuilder clearItems() {
        this.items.clear();
        return this;
    }

    @NotNull
    @Override
    public Inventory build() {
        Inventory inventory;
        if (this.title == null) {
            inventory = (inventoryType != null) ?
                    Bukkit.createInventory(null, inventoryType) :
                    Bukkit.createInventory(null, rows * CHEST_COLUMNS_PER_ROW);
        } else {
            String title = ChatUtils.get(this.title);
            inventory = (inventoryType != null) ?
                    Bukkit.createInventory(null, inventoryType, title) :
                    Bukkit.createInventory(null, rows * CHEST_COLUMNS_PER_ROW, title);
        }
        items.forEach(inventory::setItem);
        return inventory;
    }
}
