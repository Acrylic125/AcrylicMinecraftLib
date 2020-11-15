package com.acrylic.universal.gui;

import com.acrylic.paginatedcollection.PaginatedArrayList;
import org.bukkit.inventory.ItemStack;

public class PaginatedGUI extends PrivateGUIBuilder {

    private final PaginatedArrayList<ItemStack> paginatedItems;

    public PaginatedGUI(int elementsPerPage) {
        paginatedItems = new PaginatedArrayList<>(elementsPerPage);
    }


}
