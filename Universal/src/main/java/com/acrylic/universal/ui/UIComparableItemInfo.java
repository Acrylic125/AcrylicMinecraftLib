package com.acrylic.universal.ui;

import com.acrylic.universal.items.ItemUtils;
import com.acrylic.universal.ui.items.GUIItem;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

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

    public String getGUIItemCompound() {
        return guiItemCompound;
    }

    public String getGUIItemIdentifier() {
        return guiItemIdentifier;
    }

    @Nullable
    public Comparison createComparison(@Nullable ItemStack itemStack) {
        return (ItemUtils.isAir(itemStack)) ? null : new Comparison(this, itemStack);
    }

    /** The main comparison object. **/
    public static class Comparison {

        private final NBTItem nbtItem;
        private final ItemStack item;
        private final NBTCompound compound;
        private final String id;
        private final UIComparableItemInfo uiComparableItemInfo;

        private Comparison(@NotNull UIComparableItemInfo uiComparableItemInfo, @Nullable ItemStack item) {
            this.uiComparableItemInfo = uiComparableItemInfo;
            this.item = item;
            if (this.item != null) {
                this.nbtItem = new NBTItem(item);
                this.compound = nbtItem.getCompound(uiComparableItemInfo.getGUIItemCompound());
                this.id = (this.compound == null) ? null : this.compound.getString(uiComparableItemInfo.getGUIItemIdentifier());
            } else {
                this.nbtItem = null;
                this.compound = null;
                this.id = null;
            }
        }

        @Nullable
        public NBTItem getNbtItem() {
            return nbtItem;
        }

        @Nullable
        public ItemStack getItem() {
            return item;
        }

        @Nullable
        public NBTCompound getCompound() {
            return compound;
        }

        @Nullable
        public String getID() {
            return id;
        }

        @NotNull
        public UIComparableItemInfo getUiComparableItemInfo() {
            return uiComparableItemInfo;
        }

        public byte getByte(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getByte(key);
        }

        public short getShort(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getShort(key);
        }

        public int getInteger(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getInteger(key);
        }

        public long getLong(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getLong(key);
        }

        public float getFloat(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getFloat(key);
        }

        public double getDouble(@NotNull String key) {
            return (this.compound == null) ? 0 : this.compound.getDouble(key);
        }

        @Nullable
        public String getString(@NotNull String key) {
            return (this.compound == null) ? null : this.compound.getString(key);
        }


    }

    public Wrapper wrapItem(@NotNull ItemStack item) {
        return new Wrapper(this, item);
    }

    /**
     * Wraps the item with the required NBT info.
     */
    public static class Wrapper {
        private final NBTItem nbtItem;
        private final NBTCompound compound;
        private final UIComparableItemInfo uiComparableItemInfo;

        private Wrapper(@NotNull UIComparableItemInfo uiComparableItemInfo, @NotNull ItemStack item) {
            this.uiComparableItemInfo = uiComparableItemInfo;
            this.nbtItem = new NBTItem(item);
            String compoundName = uiComparableItemInfo.getGUIItemCompound();
            NBTCompound retrieved = nbtItem.getCompound(compoundName);
            if (retrieved == null)
                retrieved = nbtItem.addCompound(compoundName);
            this.compound = retrieved;
        }

        public Wrapper idByGUIItem(@NotNull GUIItem guiItem) {
            return id(guiItem.getID());
        }

        public Wrapper randomID() {
            return id(UUID.randomUUID().toString());
        }

        public Wrapper id(@NotNull String id) {
            this.compound.setString(uiComparableItemInfo.getGUIItemIdentifier(), id);
            return this;
        }

        public ItemStack wrap() {
            return nbtItem.getItem();
        }

        public Wrapper set(@NotNull String key, @Nullable Object value) {
            this.compound.setObject(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, byte value) {
            this.compound.setByte(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, short value) {
            this.compound.setShort(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, int value) {
            this.compound.setInteger(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, long value) {
            this.compound.setLong(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, float value) {
            this.compound.setFloat(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, double value) {
            this.compound.setDouble(key, value);
            return this;
        }

        public Wrapper set(@NotNull String key, String value) {
            this.compound.setString(key, value);
            return this;
        }

    }

}
