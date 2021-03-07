package com.acrylic.universal.gui.components;

import com.acrylic.universal.gui.GUI;
import com.acrylic.universal.gui.InventoryDetails;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface GUIComponent {

    default void applyComponentToInventory(@NotNull Inventory inventory) {
        applyComponentToInventory(new InventoryDetails(null, inventory));
    }

    void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails);

    @NotNull
    default Object getComponentID() {
        return getClass();
    }

    default boolean isAllowedToBeAddedToGUI(@NotNull GUI gui) {
        return true;
    }

    default boolean shouldInitializeOnCall() {
        return true;
    }

    default boolean shouldRefreshOnCall() {
        return true;
    }

}
