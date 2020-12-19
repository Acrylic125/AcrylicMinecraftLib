package com.acrylic.universal.items;

import com.acrylic.universal.text.ChatUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.regex.Pattern;

public class ItemUtils {

    private static final Pattern MATERIAL_SPACING = Pattern.compile("_");

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

    public static boolean isAir(@Nullable ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }

    public static boolean isNotAir(@Nullable ItemStack item) {
        return !isAir(item);
    }

}
