package com.acrylic.universal.ui.paginated;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AdaptablePageButton extends PageButton {

    public AdaptablePageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        super(paginatedComponent, item);
    }

}
