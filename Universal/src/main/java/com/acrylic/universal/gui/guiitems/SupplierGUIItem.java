package com.acrylic.universal.gui.guiitems;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * This is a simple implementation for conditional item presets.
 */
public class SupplierGUIItem extends AbstractGUIItem {

    /**
     * This is the supplier.
     */
    private Supplier<ItemStack> supplier;

    public SupplierGUIItem(int slot, Supplier<ItemStack> supplier) {
        super(slot);
        this.supplier = supplier;
    }

    @Override
    public ItemStack getItem() {
        return (supplier != null) ? supplier.get() : null;
    }
}
