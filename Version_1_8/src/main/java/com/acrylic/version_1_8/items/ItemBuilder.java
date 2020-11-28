package com.acrylic.version_1_8.items;

import com.acrylic.universal.items.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder implements AbstractItemBuilder {

    private final ItemStack item;
    private ItemMeta itemMeta;

    public static ItemBuilder of(Material material, int amount) {
        return of(new ItemStack(material, amount));
    }

    public static ItemBuilder of(Material material) {
        return of(new ItemStack(material));
    }

    public static ItemBuilder of(ItemStack item) {
        return new ItemBuilder(item);
    }

    private ItemBuilder(ItemStack item) {
        this.item = item;
        this.itemMeta = item.getItemMeta();
    }

    @Override
    public AbstractItemBuilder meta(ItemMeta meta) {
        itemMeta = meta;
        return this;
    }

    @Override
    public ItemMeta meta() {
        return itemMeta;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public AbstractItemBuilder damage(short damage) {
        item.setDurability(damage);
        return this;
    }

    @Override
    public AbstractItemBuilder durability(short durability) {
        return damage(durability);
    }
}
