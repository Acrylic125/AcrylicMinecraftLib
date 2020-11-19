package com.acrylic.universal.gui.guiitems;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

/**
 * This is a simple implementation for conditional item presets.
 */
@Setter @Getter
public class SupplierGUIItem implements AbstractGUIItem {

    /**
     * This is the supplier.
     */
    private Supplier<ItemStack> supplier;
    private final int slot;

    public SupplierGUIItem(int slot, Supplier<ItemStack> supplier) {
        this.slot = slot;
        this.supplier = supplier;
    }

    @Override
    public ItemStack getItem() {
        return (supplier != null) ? supplier.get() : null;
    }

    @Override
    public int getSlot() {
        return slot;
    }
}
