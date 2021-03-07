package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.InventoryDetails;
import com.acrylic.universal.gui.components.GUIItemComponent;
import com.acrylic.universal.gui.items.GUIItem;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedCollection;

import java.util.Collection;

public interface PageableGUIItemComponent extends PageableGUIComponent {

    @Override
    @NotNull
    GUIItemComponent getGUIComponent();

    @NotNull
    PaginatedCollection<GUIItem> getAllPageableItems();

    @Override
    default void applyPageToInventory(@NotNull InventoryDetails inventoryDetails, int page) {
        formatPageToInventory(inventoryDetails, getAllPageableItems().getPageList(page), page);
    }

    void formatPageToInventory(@NotNull InventoryDetails inventoryDetails, @NotNull Collection<GUIItem> itemsToFormatWith, int page);

    @Override
    default int getFirstPage() {
        return 1;
    }

    @Override
    default int getLastPage() {
        return getAllPageableItems().getLastPage();
    }
}
