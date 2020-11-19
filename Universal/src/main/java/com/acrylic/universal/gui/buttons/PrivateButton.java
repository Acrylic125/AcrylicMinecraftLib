package com.acrylic.universal.gui.buttons;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PrivateButton extends Button {

    public PrivateButton(int slot, @NotNull ItemStack item) {
        super(slot, item);
    }

    public ItemStack getDefaultItem() {
        return super.getItem();
    }

    @Override
    public String getId() {
        return "PrivateButton";
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(getDefaultItem());
    }

}
