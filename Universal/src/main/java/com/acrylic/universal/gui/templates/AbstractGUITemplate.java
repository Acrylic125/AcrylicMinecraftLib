package com.acrylic.universal.gui.templates;

import com.acrylic.universal.gui.buttons.AbstractButton;
import com.acrylic.universal.gui.buttons.AbstractButtons;
import com.acrylic.universal.gui.guiitems.GUIItem;
import com.acrylic.universal.gui.guiitems.PlayerGUIItem;
import com.acrylic.universal.gui.modules.AbstractGUIModule;
import com.acrylic.universal.gui.modules.GUIModule;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter @Getter
public abstract class AbstractGUITemplate {

    private final AbstractGUIModule<GUIItem> guiItems = new GUIModule<>();
    private AbstractGUIModule<PlayerGUIItem> playerSpecificItems = null;

    public void addGUIItem(int slot, ItemStack item) {
        guiItems.addItem(new GUIItem(slot, item));
    }

    public void apply(@NotNull final Inventory inventory) {
        applyDefaultToInventory(inventory);
    }

    public void apply(@NotNull final Inventory inventory, @Nullable final Player viewer) {
        applyDefaultToInventory(inventory);
        if (viewer != null && playerSpecificItems != null) {
            playerSpecificItems.forEach(guiItem -> {
                guiItem.applyTo(inventory, viewer);
            });
        }
    }

    private void applyDefaultToInventory(Inventory inventory) {
         guiItems.forEach(guiItem -> {
             guiItem.applyTo(inventory);
        });
    }

}
