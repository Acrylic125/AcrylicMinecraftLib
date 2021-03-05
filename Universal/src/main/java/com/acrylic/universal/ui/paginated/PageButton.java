package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import com.acrylic.universal.ui.items.GUIClickableItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class PageButton implements GUIClickableItem {

    private static final String PAGE_KEY = "page";

    private final PaginatedComponent paginatedComponent;
    private final String id = UUID.randomUUID().toString();
    private ItemStack item;
    private boolean active = true;

    public PageButton(@NotNull PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        this.paginatedComponent = paginatedComponent;
        setItem(item);
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item;
    }

    @NotNull
    public PaginatedComponent getPaginatedComponent() {
        return paginatedComponent;
    }

    @NotNull
    @Override
    public String getID() {
        return id;
    }

    @Nullable
    public ItemStack getItem(InventoryDetails openDetails, int page) {
        UIComparableItemInfo.Wrapper wrapper = wrapperGUIItem(item);
        return (wrapper == null) ? null : wrapper.set(PAGE_KEY, page).wrap();
    }

    @Nullable
    @Override
    public ItemStack getItem(InventoryDetails openDetails) {
        return getItem(openDetails, 1);
    }

    @Override
    public boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Comparison uiComparableComparisonInfo) {
        return id.equals(uiComparableComparisonInfo.getID());
    }

    @Override
    public void onClicked(InventoryClickEvent event, @NotNull UIComparableItemInfo.Comparison comparison) {

    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
