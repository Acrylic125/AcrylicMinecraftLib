package com.acrylic.universal.gui.buttons;

import com.acrylic.universal.gui.AbstractGUIBuilder;
import com.acrylic.universal.gui.modules.AbstractGUIModule;
import com.acrylic.universal.items.ItemUtils;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class AbstractButtons implements AbstractGUIModule<AbstractButton> {

    private final ArrayList<AbstractButton> buttons = new ArrayList<>();

    public void handle(@NotNull InventoryClickEvent clickEvent, @NotNull AbstractGUIBuilder guiBuilder) {
        Inventory clickedInventory = clickEvent.getClickedInventory();
        if (!clickEvent.getView().getTopInventory().equals(clickedInventory))
            return;
        ItemStack clickedItem = clickEvent.getCurrentItem();
        if (ItemUtils.isAir(clickedItem))
            return;
        assert clickedItem != null;
        AbstractButton button = queryButton(clickedItem);
        if (button != null)
            button.getButtonAction().run(clickedInventory, clickEvent.getView(), guiBuilder);
    }

    public AbstractButton queryButton(@NotNull ItemStack item) {
        NBTCompound compound = new NBTItem(item).getCompound(AbstractButton.ID);
        String id = compound.getString(AbstractButton.ID);
        String subId = compound.getString(AbstractButton.SUB_ID);
        if (id != null && subId != null)
            for (AbstractButton button : buttons)
                if (button.isThisButton(id, subId))
                    return button;
        return null;
    }

    @Override
    public ArrayList<AbstractButton> getList() {
        return buttons;
    }
}
