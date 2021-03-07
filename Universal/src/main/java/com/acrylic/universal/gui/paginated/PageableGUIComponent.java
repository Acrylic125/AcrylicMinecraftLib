package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.InventoryDetails;
import com.acrylic.universal.gui.components.GUIComponent;
import org.jetbrains.annotations.NotNull;

public interface PageableGUIComponent {

    int getFirstPage();

    int getLastPage();

    @NotNull
    GUIComponent getGUIComponent();

    void applyPageToInventory(@NotNull InventoryDetails inventoryDetails, int page);

}
