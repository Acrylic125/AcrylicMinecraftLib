package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIComponent;
import com.acrylic.universal.ui.components.GUIItemComponent;
import com.acrylic.universal.ui.items.GUIItem;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaginatedComponent implements GUIItemComponent {

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

    public void applyPageToInventory(@NotNull InventoryDetails inventoryDetails, int page) {
        pageableGUIComponent.applyPageToInventory(inventoryDetails, page);
        applyBase(inventoryDetails);
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        pageableGUIComponent.getGUIComponent().applyComponentToInventory(inventoryDetails);
        applyBase(inventoryDetails);
    }

    protected void applyBase(InventoryDetails inventoryDetails) {
        Inventory inventory = inventoryDetails.getInventory();
        pageButtons.forEach((slot, item) -> inventory.setItem(slot, item.getItem(inventoryDetails)));
    }

    @NotNull
    @Override
    public Collection<PageButton> getGUIItems() {
        return pageButtons.values();
    }
}
