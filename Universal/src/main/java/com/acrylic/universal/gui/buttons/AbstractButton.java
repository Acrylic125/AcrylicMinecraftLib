package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.guiitems.AbstractGUIItem;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractButton implements AbstractGUIItem {

    public static final String NBT_COMPOUND_NAME = "Button";
    public static final String NBT_ID = "id";
    public static final String NBT_SUB_ID = "subid";

    private final int slot;
    private ButtonAction buttonAction;
    private String subId = UUID.randomUUID().toString();

    public AbstractButton(int slot) {
        this.slot = slot;
    }

    public ButtonAction getButtonAction() {
        return buttonAction;
    }

    public void setButtonAction(ButtonAction buttonAction) {
        this.buttonAction = buttonAction;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public ItemStack transformButton(@NotNull ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound compound = nbtItem.getCompound(NBT_COMPOUND_NAME);
        if (compound == null)
            compound = nbtItem.addCompound(NBT_COMPOUND_NAME);
        compound.setString(NBT_ID, getId());
        compound.setString(NBT_SUB_ID, subId);
        modifyButtonNBT(compound);
        return nbtItem.getItem();
    }

    public boolean isThisButton(@NotNull String id, @NotNull String subId) {
        return id.equals(getId()) && subId.equals(this.subId);
    }

    @Override
    public int getSlot() {
        return slot;
    }

    public abstract String getId();

    public abstract void modifyButtonNBT(NBTCompound compound);

}
