package com.acrylic.universal.ui;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class helps with identifying what the item was clicked
 * in the UI.
 */
public final class UIComparableItemInfo {

    private static final UIComparableItemInfo comparableItemInfo = new UIComparableItemInfo();

    public static UIComparableItemInfo getComparableItemInfo() {
        return comparableItemInfo;
    }

    public final String guiItemCompound = "gui-item",
        guiItemIdentifier = "id";

    public Item createComparison(@Nullable ItemStack itemStack) {
        return new Item(itemStack);
    }

    public static class Item {

        private final NBTItem nbtItem;
        private final ItemStack item;

        private Item(@Nullable ItemStack item) {
            this.item = item;
            this.nbtItem = (this.item != null) ? new NBTItem(item) : null;
        }

        @Nullable
        public NBTItem getNbtItem() {
            return nbtItem;
        }

        @Nullable
        public ItemStack getItem() {
            return item;
        }
    }

}
