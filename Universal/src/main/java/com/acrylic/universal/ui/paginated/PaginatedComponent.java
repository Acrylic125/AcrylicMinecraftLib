package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface PaginatedComponent {

    void format(@NotNull InventoryDetails inventoryDetails, @NotNull Collection<ItemStack> items, int page);
    
}
