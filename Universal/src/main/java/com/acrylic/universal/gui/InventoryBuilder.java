package com.acrylic.universal.gui;

import org.bukkit.event.inventory.InventoryType;

public class InventoryBuilder implements AbstractInventoryBuilder {

    private InventoryType inventoryType;
    private int rows = 0;
    private String title;

    public static InventoryBuilder create() {
        return new InventoryBuilder();
    }

    private InventoryBuilder() {}

    @Override
    public AbstractInventoryBuilder inventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
        return this;
    }

    @Override
    public AbstractInventoryBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public AbstractInventoryBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public InventoryType getInventoryType() {
        return inventoryType;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public InventoryBuilder clone() {
        return (InventoryBuilder) create()
                .title(title)
                .rows(rows)
                .inventoryType(inventoryType);
    }

}
