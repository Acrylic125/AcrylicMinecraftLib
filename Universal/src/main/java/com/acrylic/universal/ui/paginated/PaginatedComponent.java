package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIComponent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PaginatedComponent implements GUIComponent {

    private final PageableGUIComponent pageableGUIComponent;
    private final Map<Integer, PageButton> pageButtons;

    public PaginatedComponent(@NotNull PageableGUIComponent pageableGUIComponent) {
        this(pageableGUIComponent, new HashMap<>());
    }

    public PaginatedComponent(@NotNull PageableGUIComponent pageableGUIComponent, @NotNull Map<Integer, PageButton> pageButtons) {
        this.pageableGUIComponent = pageableGUIComponent;
        this.pageButtons = pageButtons;
    }

    @NotNull
    public Map<Integer, PageButton> getPageButtons() {
        return pageButtons;
    }

    public void addPageButton(int slot, @NotNull PageButton pageButton) {
        pageButtons.put(slot, pageButton);
    }

    public void removePageButton(int slot) {
        pageButtons.remove(slot);
    }

    public void clearPageButtons() {
        pageButtons.clear();
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        pageableGUIComponent.getGUIComponent().applyComponentToInventory(inventoryDetails);
        Inventory inventory = inventoryDetails.getInventory();
        pageButtons.forEach((slot, item) -> inventory.setItem(slot, item.getItem(inventoryDetails)));
    }
}
