package com.acrylic.universal.gui.buttons;

import de.tr7zw.nbtapi.NBTCompound;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Button extends AbstractButton {

    private ItemStack item;

    public Button(int slot, @NotNull ItemStack item) {
        super(slot);
        setItem(item);
    }

    public void setItem(@NotNull ItemStack item) {
        this.item = transformButton(item);
    }

    @Override
    public String getId() {
        return "NormalButton";
    }

    @Override
    public void modifyButtonNBT(NBTCompound compound) { }

    @Override
    public ItemStack getItem() {
        return item;
    }
}
