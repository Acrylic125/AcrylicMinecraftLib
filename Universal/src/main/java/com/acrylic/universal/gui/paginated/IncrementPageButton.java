package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.InventoryDetails;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncrementPageButton extends PageButton {

    public static IncrementPageButton createLastPageButton(@Nullable ItemStack item) {
        return createLastPageButton(item, -1);
    }

    public static IncrementPageButton createLastPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        return createLastPageButton(paginatedComponent, item, -1);
    }

    public static IncrementPageButton createLastPageButton(@Nullable ItemStack item, int turnBy) {
        return new IncrementPageButton(item, -turnBy);
    }

    public static IncrementPageButton createLastPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item, int turnBy) {
        return new IncrementPageButton(paginatedComponent, item, -turnBy);
    }

    public static IncrementPageButton createNextPageButton(@Nullable ItemStack item) {
        return createNextPageButton(item, 1);
    }

    public static IncrementPageButton createNextPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        return createNextPageButton(paginatedComponent, item, 1);
    }

    public static IncrementPageButton createNextPageButton(@Nullable ItemStack item, int turnBy) {
        return new IncrementPageButton(item, turnBy);
    }

    public static IncrementPageButton createNextPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item, int turnBy) {
        return new IncrementPageButton(paginatedComponent, item, turnBy);
    }

    private int pageIncrement = 1;
    private boolean returnNullIfPageOutOfBounds = true;

    public IncrementPageButton(@Nullable ItemStack item) {
        super(item);
    }

    public IncrementPageButton(@Nullable ItemStack item, int pageIncrement) {
        super(item);
        this.pageIncrement = pageIncrement;
    }

    public IncrementPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        super(paginatedComponent, item);
    }

    public IncrementPageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item, int pageIncrement) {
        super(paginatedComponent, item);
        this.pageIncrement = pageIncrement;
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
        page += pageIncrement;
        PaginatedComponent paginatedComponent = getPaginatedComponent();
        if (paginatedComponent == null)
            throw new NullPointerException("No paginated component set!");
        return (returnNullIfPageOutOfBounds && paginatedComponent.isPageOutOfBounds(page)) ? null : super.getItem(openDetails, page);
    }
}
