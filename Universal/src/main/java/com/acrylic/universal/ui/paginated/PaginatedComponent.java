package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.GUI;
import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import com.acrylic.universal.ui.components.GUIItemComponent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaginatedComponent implements GUIItemComponent {

    private final PageableGUIComponent pageableGUIComponent;
    private final Map<Integer, PageButton> pageButtons;

    public static Builder builder(@NotNull PageableGUIComponent pageableGUIComponent) {
        return new Builder(pageableGUIComponent);
    }

    public static class Builder {

        private final PaginatedComponent paginatedComponent;

        private Builder(PageableGUIComponent pageableGUIComponent) {
            this.paginatedComponent = new PaginatedComponent(pageableGUIComponent);
        }

        public Builder addPageButton(int slot, @NotNull PageButton pageButton) {
            this.paginatedComponent.addPageButton(slot, pageButton);
            pageButton.setPaginatedComponent(paginatedComponent);
            return this;
        }

        public Builder removePageButton(int slot, @NotNull PageButton pageButton) {
            this.paginatedComponent.removePageButton(slot);
            return this;
        }

        public Builder clearPageButtons() {
            this.paginatedComponent.clearPageButtons();
            return this;
        }

        public PaginatedComponent build() {
            return paginatedComponent;
        }
    }

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
        applyBase(inventoryDetails, page);
    }

    @Override
    public void applyComponentToInventory(@NotNull InventoryDetails inventoryDetails) {
        applyPageToInventory(inventoryDetails, 1);
    }

    protected void applyBase(InventoryDetails inventoryDetails, int page) {
        Inventory inventory = inventoryDetails.getInventory();
        pageButtons.forEach((slot, item) -> {
            ItemStack itemStack = item.getItem(inventoryDetails, page);
            if (itemStack != null)
                inventory.setItem(slot, itemStack);
        });
    }

    @NotNull
    @Override
    public Collection<PageButton> getGUIItems() {
        return pageButtons.values();
    }

    public boolean isPageOutOfBounds(int page) {
        return page < pageableGUIComponent.getFirstPage() || page > pageableGUIComponent.getLastPage();
    }

    @Override
    public boolean findAndRunButton(GUI gui, InventoryClickEvent event, UIComparableItemInfo.Comparison comparison) {
        boolean found = GUIItemComponent.super.findAndRunButton(gui, event, comparison);
        return found ||
                ((pageableGUIComponent instanceof GUIItemComponent) && ((GUIItemComponent) pageableGUIComponent).findAndRunButton(gui, event, comparison));
    }

    @Override
    public boolean shouldRefreshOnCall() {
        return false;
    }
}
