package com.acrylic.universal.gui.buttons;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PageButton extends Button {

    public PageButton(int slot, @NotNull ItemStack item) {
        super(slot, item);
    }

    @Override
    public String getId() {
        return "PageButton";
    }
}
