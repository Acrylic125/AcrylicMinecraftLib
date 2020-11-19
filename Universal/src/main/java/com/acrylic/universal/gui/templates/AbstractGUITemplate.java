package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.guiitems.AbstractPlayerGUIItem;
import com.acrylic.universal.gui.guiitems.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class AbstractGUITemplate {

    private final ArrayList<GUIItem> guiItems = new ArrayList<>();
    private final ArrayList<AbstractPlayerGUIItem> playerSpecificItems = new ArrayList<>();

    public AbstractPlayerGUIItem getPlayerGUIItem(int slot) {
        for (AbstractPlayerGUIItem guiItem : playerSpecificItems)
            if (guiItem.getSlot() == slot)
                return guiItem;
        return null;
    }

    public AbstractGUITemplate addPlayerGUIItem(@NotNull AbstractPlayerGUIItem guiItem) {
        AbstractPlayerGUIItem check = getPlayerGUIItem(guiItem.getSlot());
        if (check != null)
            playerSpecificItems.remove(check);
        playerSpecificItems.add(guiItem);
        return this;
    }

    public AbstractGUITemplate addPlayerGUIItem(@NotNull AbstractPlayerGUIItem... guiItems) {
        for (AbstractPlayerGUIItem guiItem : guiItems)
            addPlayerGUIItem(guiItem);
        return this;
    }

    public GUIItem getGUIItem(int slot) {
        for (GUIItem guiItem : guiItems)
            if (guiItem.getSlot() == slot)
                return guiItem;
        return null;
    }

    public AbstractGUITemplate addGUIItem(@NotNull GUIItem guiItem) {
        GUIItem check = getGUIItem(guiItem.getSlot());
        if (check != null)
            guiItems.remove(check);
        guiItems.add(guiItem);
        return this;
    }

    public AbstractGUITemplate addGUIItem(int slot, ItemStack item) {
        return addGUIItem(new GUIItem(slot, item));
    }

    public AbstractGUITemplate addGUIItem(@NotNull GUIItem... guiItems) {
        for (GUIItem guiItem : guiItems)
            addGUIItem(guiItem);
        return this;
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
