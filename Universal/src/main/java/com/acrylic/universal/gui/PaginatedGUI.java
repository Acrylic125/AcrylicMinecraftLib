package com.acrylic.universal.gui;

import com.acrylic.paginatedcollection.PaginatedArrayList;
import org.bukkit.inventory.ItemStack;

public class PaginatedGUI extends PrivateGUIBuilder {

    private final PaginatedArrayList<ItemStack> paginatedItems;

    public PaginatedGUI(InventoryBuilder inventoryBuilder, int elementsPerPage) {
        super(inventoryBuilder);
        paginatedItems = new PaginatedArrayList<>(elementsPerPage);
    }


}
