package com.acrylic.universal.gui.paginated;

import com.acrylic.universal.gui.GUI;
import com.acrylic.universal.gui.InventoryDetails;
import com.acrylic.universal.gui.UIComparableItemInfo;
import com.acrylic.universal.gui.items.GUIClickableItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class PageButton implements GUIClickableItem {

    private static final String PAGE_KEY = "page";

    private PaginatedComponent paginatedComponent;
    private final String id = UUID.randomUUID().toString();
    private ItemStack item;
    private boolean active = true;

    public PageButton(@Nullable ItemStack item) {
        setItem(item);
    }

    public PageButton(@Nullable PaginatedComponent paginatedComponent, @Nullable ItemStack item) {
        this.paginatedComponent = paginatedComponent;
        setItem(item);
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item;
    }

    public void setPaginatedComponent(@Nullable PaginatedComponent paginatedComponent) {
        this.paginatedComponent = paginatedComponent;
    }

    @Nullable
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
    public void onClicked(@NotNull GUI gui, @NotNull InventoryClickEvent event, @NotNull UIComparableItemInfo.Comparison comparison) {
        validateUse();
        int page = comparison.getInteger(PAGE_KEY);
        InventoryDetails inventoryDetails = new InventoryDetails((Player) event.getView().getPlayer(), event.getInventory());
        gui.refresh(inventoryDetails);
        paginatedComponent.applyPageToInventory(inventoryDetails, page);
    }

    private void validateUse() {
        if (paginatedComponent == null)
            throw new IllegalStateException("The paginated component must be set.");
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

}
