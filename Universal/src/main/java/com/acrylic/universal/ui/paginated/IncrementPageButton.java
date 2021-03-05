package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncrementPageButton extends PageButton {

    private int pageIncrement = 1;
    private boolean returnNullIfPageOutOfBounds = true;

    public IncrementPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        super(paginatedComponent, item);
    }

    public int getPageIncrement() {
        return pageIncrement;
    }

    public void setPageIncrement(int pageIncrement) {
        this.pageIncrement = pageIncrement;
    }

    public boolean isReturnNullIfPageOutOfBounds() {
        return returnNullIfPageOutOfBounds;
    }

    public void setReturnNullIfPageOutOfBounds(boolean returnNullIfPageOutOfBounds) {
        this.returnNullIfPageOutOfBounds = returnNullIfPageOutOfBounds;
    }

    @Nullable
    @Override
    public ItemStack getItem(InventoryDetails openDetails, int page) {
        return (returnNullIfPageOutOfBounds && getPaginatedComponent().isPageOutOfBounds(page)) ? null : super.getItem(openDetails, page + pageIncrement);
    }
}
