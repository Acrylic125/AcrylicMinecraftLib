package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.guiitems.AbstractGUIItem;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Setter @Getter
public abstract class AbstractButton implements AbstractGUIItem {

    public static final String COMPOUND_NAME = "Button";
    public static final String ID = "id";
    public static final String SUB_ID = "subid";

    private final int slot;
    private ButtonAction buttonAction;
    private String subId = UUID.randomUUID().toString();

    public AbstractButton(int slot) {
        this.slot = slot;
    }

    public ItemStack transformButton(@NotNull ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound compound = nbtItem.getCompound(COMPOUND_NAME);
        compound.setString(ID, getId());
        compound.setString(ID, subId);
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
