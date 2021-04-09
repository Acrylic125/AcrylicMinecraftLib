package com.acrylic.universal.items;

import com.acrylic.universal.MCLib;
import com.acrylic.universal.items.itemdropproection.ItemDropProtected;
import com.acrylic.universal.items.itemdropproection.PlayerItemDropProtected;
import com.acrylic.universal.text.ChatUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class ItemUtils {

    private static final Pattern MATERIAL_SPACING = Pattern.compile("_");

    @NotNull
    public static ItemTypeAnalyzer getItemTypeAnalyzer() {
        return MCLib.getLib().getItemTypeAnalyzer();
    }

    @NotNull
    public static String getNameWithAmount(@Nullable ItemStack item) {
        return getNameWithAmount(item, "&7", "x", "", "");
    }

    @NotNull
    public static String getNameWithAmount(@Nullable ItemStack item, @NotNull String amountPrefix, @NotNull String amountSuffix, @NotNull String namePrefix, @NotNull String nameSuffix) {
        int amount = (item == null) ? 0 : item.getAmount();
        return ChatUtils.get("&r" + amountPrefix + amount + amountSuffix + "&r " +
                namePrefix + getName(item) + nameSuffix + "&r");
    }

    @NotNull
    public static String getName(@Nullable ItemStack item) {
        if (item == null)
            return getName((Material) null);
        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return getName(item.getType());
        return (meta.hasDisplayName()) ? meta.getDisplayName() : getName(item.getType());
    }

    @NotNull
    public static String getName(@Nullable Material material) {
        if (material == null)
            material = Material.AIR;
        return WordUtils.capitalize(MATERIAL_SPACING.matcher(material.toString().toLowerCase(Locale.ENGLISH)).replaceAll(" "));
    }

    public static boolean isLiquid(@NotNull Material material) {
        return ItemUtils.getItemTypeAnalyzer().isLiquidMaterial(material);
    }

    public static boolean isLiquid(@NotNull Block block) {
        return isLiquid(block.getType());
    }

    public static boolean isLiquid(@NotNull ItemStack item) {
        return ItemUtils.getItemTypeAnalyzer().isLiquid(item);
    }

    public static boolean isAir(@Nullable Material material) {
        return ItemUtils.getItemTypeAnalyzer().isAir(material);
    }

    public static boolean isAir(@Nullable Block block) {
        return ItemUtils.getItemTypeAnalyzer().isAir(block);
    }

    public static boolean isAir(@Nullable ItemStack item) {
        return ItemUtils.getItemTypeAnalyzer().isAir(item);
    }

    public static boolean isNotAir(@Nullable ItemStack item) {
        return !isAir(item);
    }

    @NotNull
    public static ItemStack[] getInventory(@NotNull Player player) {
        return player.getInventory().getContents();
    }

    public static int getAmount(@NotNull Player player, @Nullable ItemStack item) {
        if (isAir(item))
            return 0;
        int total = 0;
        for (ItemStack inventoryItem : getInventory(player)) {
            if (!isAir(inventoryItem) && inventoryItem.isSimilar(item))
                total += inventoryItem.getAmount();
        }
        return total;
    }

    public static void giveItem(@NotNull Player player, @NotNull ItemStack item) {
        giveItem(player, item, null);
    }

    public static void giveItem(@NotNull Player player, @NotNull ItemStack item, @Nullable ItemGiveAction then) {
        player.getInventory().addItem(item);
        if (then != null)
            then.accept(item, player, hasEnoughSpace(player,item), false, -1);
    }

    public static void giveItem(@NotNull Player player, @NotNull ItemStack... item) {
        player.getInventory().addItem(item);
    }

    public static boolean giveItem(@NotNull Player player, @NotNull ItemStack item, boolean dropProtection) {
        return giveItem(player, item, dropProtection, -1);
    }


    public static boolean giveItem(@NotNull Player player, @NotNull ItemStack item, boolean dropProtection, long time) {
        return giveItem(player, item, dropProtection, time, null);
    }

    /**
     * @param dropProtection If the item dropped should have drop protection support.
     * @return See above.
     */
    public static boolean giveItem(@NotNull Player player, @NotNull ItemStack item, boolean dropProtection, long time, @Nullable ItemGiveAction then) {
        boolean hasEnoughSpace = hasEnoughSpace(player,item);
        if (hasEnoughSpace)
            player.getInventory().addItem(item);
        else
            dropItem(player, item, dropProtection, time);
        if (then != null)
            then.accept(item, player, hasEnoughSpace, dropProtection, time);
        return hasEnoughSpace;
    }

    @NotNull
    public static Item dropItem(@NotNull Location location, @NotNull ItemStack item) {
        return Objects.requireNonNull(location.getWorld()).dropItem(location, item);
    }

    public static void dropItem(@NotNull Player player, @NotNull ItemStack item, boolean dropProtection) {
        dropItem(player.getLocation(), item, dropProtection, -1, player);
    }

    public static void dropItem(@NotNull Player player, @NotNull ItemStack item, boolean dropProtection, long time) {
        dropItem(player.getLocation(), item, dropProtection, time, player);
    }

    public static void dropItem(@NotNull Location location, @NotNull ItemStack item, boolean dropProtection, long time, @NotNull Player... player) {
        Item dropItem = dropItem(location, item);
        if (dropProtection) {
            PlayerItemDropProtected dropProtected = (time > 0) ? ItemDropProtected.getDropProtectedItem(dropItem, time) : ItemDropProtected.getDropProtectedItem(dropItem);
            dropProtected.addEntity(player);
        }
    }

    public static boolean hasEnoughSpace(@NotNull Player player, @Nullable ItemStack item) {
        return hasEnoughSpace(player.getInventory(), item);
    }

    public static boolean hasEnoughSpace(@NotNull Inventory inventory, @Nullable ItemStack item) {
        if (ItemUtils.isAir(item))
            return true;
        assert item != null;
        int quantityToCompare = 0;
        for (ItemStack inventoryItem : inventory) {
            if (ItemUtils.isAir(inventoryItem))
                quantityToCompare += item.getMaxStackSize();
            else if (inventoryItem.isSimilar(item))
                quantityToCompare += (item.getMaxStackSize() - inventoryItem.getAmount());
        }
        return item.getAmount() <= quantityToCompare;
    }

}
