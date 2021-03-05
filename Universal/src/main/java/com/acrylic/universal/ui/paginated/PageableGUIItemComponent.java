package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIComponent;
import com.acrylic.universal.ui.components.GUIItemComponent;
import com.acrylic.universal.ui.items.GUIItem;
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
