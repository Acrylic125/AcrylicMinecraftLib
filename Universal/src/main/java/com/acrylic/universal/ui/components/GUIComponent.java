package com.acrylic.universal.ui.components;

import com.acrylic.universal.ui.GUI;
import com.acrylic.universal.ui.InventoryDetails;
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

}
