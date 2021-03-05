package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.components.GUIComponent;
import com.acrylic.universal.ui.uiformats.UIFormat;
import org.jetbrains.annotations.NotNull;

public interface PageableGUIComponent {

    int getFirstPage();

    int getLastPage();

    @NotNull
    GUIComponent getGUIComponent();

    void applyPageToInventory(@NotNull InventoryDetails inventoryDetails, int page);

}
