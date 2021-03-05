package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import paginatedcollection.PaginatedCollection;

import java.util.Collection;

public interface PageableUIFormat {

    @NotNull
    PaginatedCollection<GUIItem> getAllPageableItems();

    void formatPageByCollection(@NotNull InventoryDetails inventoryDetails, @NotNull Collection<GUIItem> itemsToFormatInPage);

    @SuppressWarnings("all")
    default void formatPage(@NotNull InventoryDetails inventoryDetails, PaginatedCollection<GUIItem> ofCollection, int page) {
        formatPageByCollection(inventoryDetails, ofCollection.getPageList(page));
    }

    default void formatPage(@NotNull InventoryDetails inventoryDetails, PaginatedCollection<GUIItem> ofCollection) {
        formatPage(inventoryDetails, ofCollection, 1);
    }

    default void formatPage(@NotNull InventoryDetails inventoryDetails, int page) {
        formatPage(inventoryDetails, getAllPageableItems(), page);
    }

    default void formatPage(@NotNull InventoryDetails inventoryDetails) {
        formatPage(inventoryDetails, 1);
    }

}
