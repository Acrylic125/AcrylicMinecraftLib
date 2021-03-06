package com.acrylic.universal.items;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public interface AbstractItemBuilder {

    AbstractItemBuilder meta(ItemMeta meta);

    ItemMeta meta();

    default AbstractItemBuilder meta(Consumer<ItemMeta> metaConsumer) {
        metaConsumer.accept(meta());
        return this;
    }

    ItemStack getItem();

    AbstractItemBuilder damage(short damage);

    AbstractItemBuilder durability(short durability);

    default AbstractItemBuilder amount(int amount) {
        getItem().setAmount(amount);
        return this;
    }

    default AbstractItemBuilder shiny(boolean shiny) {
        boolean isRod = (getItem().getType().equals(Material.FISHING_ROD));
        return (shiny) ?
                enchant(isRod ? Enchantment.OXYGEN : Enchantment.LURE, 1)
                       .itemFlags(ItemFlag.HIDE_ENCHANTS)
                :
                removeEnchant(isRod ? Enchantment.OXYGEN : Enchantment.LURE)
                        .removeItemFlags(ItemFlag.HIDE_ENCHANTS);
    }

    default AbstractItemBuilder itemFlags(ItemFlag... itemFlags) {
        meta().addItemFlags(itemFlags);
        return this;
    }

    default AbstractItemBuilder removeItemFlags(ItemFlag... itemFlags) {
        meta().removeItemFlags(itemFlags);
        return this;
    }

    default AbstractItemBuilder attribute(Attribute attribute, AttributeModifier attributeModifier) {
        meta().addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    default AbstractItemBuilder removeAttribute(Attribute attribute, AttributeModifier attributeModifier) {
        meta().removeAttributeModifier(attribute, attributeModifier);
        return this;
    }

    default AbstractItemBuilder removeEnchant(@NotNull Enchantment enchantment) {
        meta().removeEnchant(enchantment);
        return this;
    }

    default AbstractItemBuilder enchant(@NotNull Enchantment enchantment, int level) {
        meta().addEnchant(enchantment, level, true);
        return this;
    }

    default AbstractItemBuilder name(String name) {
        return rawName(ChatUtils.get(name));
    }

    default AbstractItemBuilder rawName(String name) {
        meta().setDisplayName(name);
        return this;
    }

    default AbstractItemBuilder lore(String... lore) {
        final LinkedList<String> l = new LinkedList<>();
        for (String s : lore)
            l.add(ChatUtils.get(s));
        return rawLore(l);
    }

    default AbstractItemBuilder lore(Collection<String> lore) {
        final LinkedList<String> l = new LinkedList<>();
        for (String s : lore)
            l.add(ChatUtils.get(s));
        return rawLore(l);
    }

    default AbstractItemBuilder rawLore(List<String> lore) {
        meta().setLore(lore);
        return this;
    }

    default ItemStack build() {
        ItemStack item = getItem();
        item.setItemMeta(meta());
        return item;
    }

}
