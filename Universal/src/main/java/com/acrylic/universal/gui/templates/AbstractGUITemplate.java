package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.guiitems.GUIItem;
import com.acrylic.universal.gui.guiitems.PlayerGUIItem;
import com.acrylic.universal.gui.modules.AbstractGUIModule;
import com.acrylic.universal.gui.modules.GUIModule;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractGUITemplate {

    private final AbstractGUIModule<GUIItem> guiItems = new GUIModule<>();
    private final AbstractGUIModule<PlayerGUIItem> playerSpecificItems = new GUIModule<>();

    public void addGUIItem(int slot, ItemStack item) {
        guiItems.addItem(new GUIItem(slot, item));
    }

    public void apply(@NotNull final Inventory inventory) {
        applyDefaultToInventory(inventory);
    }

    public void apply(@NotNull final Inventory inventory, @Nullable final Player viewer) {
        applyDefaultToInventory(inventory);
        if (viewer == null)
            return;
        final int size = inventory.getSize();
        playerSpecificItems.forEach(guiItem -> {
            int s = guiItem.getSlot();
            if (s < size)
                guiItem.applyTo(inventory, viewer);
        });
    }

    private void applyDefaultToInventory(Inventory inventory) {
        final int size = inventory.getSize();
        guiItems.forEach(guiItem -> {
            int s = guiItem.getSlot();
            if (s < size)
                guiItem.applyTo(inventory);
        });
    }

}
