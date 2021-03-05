package com.acrylic.universal.ui.paginated;

import com.acrylic.universal.ui.InventoryDetails;
import com.acrylic.universal.ui.UIComparableItemInfo;
import com.acrylic.universal.ui.items.GUIClickableItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PageButton implements GUIClickableItem {

    private final String id = UUID.randomUUID().toString();
    private ItemStack item;

    public PageButton(@Nullable ItemStack item) {
        setItem(item);
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = wrapGUIItem(item);
    }

    @NotNull
    @Override
    public String getID() {
        return id;
    }

    @Nullable
    @Override
    public ItemStack getItem(InventoryDetails openDetails) {
        return item;
    }

    @Override
    public boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Comparison uiComparableComparisonInfo) {
        return false;
    }

    @Override
    public void onClicked(InventoryClickEvent event) {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
