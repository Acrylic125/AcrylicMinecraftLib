package com.acrylic.universal.ui.uiitems;

import com.acrylic.universal.ui.UIComparableItemInfo;
import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.OpenDetails;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class BasicGUIItem implements GUIItem {

    private ItemStack item;
    private final String id = UUID.randomUUID().toString();

    public BasicGUIItem(@Nullable ItemStack item) {
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
    public ItemStack getItem(OpenDetails openDetails) {
        return item;
    }

    @Override
    public boolean doesItemMatchWithThis(@NotNull UIComparableItemInfo.Item uiComparableItemInfo) {
        return id.equals(uiComparableItemInfo.getID());
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }
}
