package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.javamaps.MapClimber;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Example:
 *
 * example_1:
 *   variables:
 *     quantity: (int)4-20,4%1-2
 *     success: (float)<2>1-100
 *     power: (int)5%1|2,3%4-5,1%11-13,0.5%20|25
 *   item:
 *     name: "&b&l@power% Power"
 *     quantity: "@quantity"
 *     material: DIAMOND
 *     damage: 0
 *     lore:
 *       - "&7Grants &a&l+@power%&r&7 to all enchants."
 *       - ""
 *       - "&7Drag and Drop to apply"
 *       - "&a&lSuccess Rate: @success%"
 *     enchants:
 *       SHARPNESS: 5
 *     tags:
 *       test:
 *         sample_string_tag: "Hello! This tag is in the test compound."
 *       percentage_variable_sample: (int)@success
 */
public final class ItemStackParser extends AbstractVariableParser<ItemStack> {

    public static final String COMPOUND_ITEM = "item";
    public static final String KEY_MATERIAL = "material";
    public static final String KEY_DAMAGE = "damage";
    public static final String KEY_NAME = "name";
    public static final String KEY_LORE = "lore";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_ENCHANTS = "enchants";
    public static final String KEY_TAGS = "tags";

    public ItemStackParser() { }

    public ItemStackParser(FileEditor fileEditor) {
        super(fileEditor);
    }

    @Override
    public Map<String, Object> serialize(ItemStack toParse) {
        return null;
    }

    @Override
    public ItemStack parse(Map<String, Object> parseFrom) {
        return new ItemParser(this, parseFrom).get();
    }

    /**
     * Item Parser.
     */
    private static class ItemParser {

        private final ParserMap<ItemStack> parserMap;

        @SuppressWarnings("unchecked")
        public ItemParser(ItemStackParser itemStackParser, Map<String, Object> parseFrom) {
            parserMap = new ParserMap<>(itemStackParser, parseFrom.get(COMPOUND_ITEM));
        }

        public ItemStack get() {
            Material material = getMaterial();
            if (material == null)
                return null;
            ItemStack item = new ItemStack(material, getAmount());
            item.setDurability(getDamage());
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta != null) {
                String name = getName();
                if (name != null)
                    itemMeta.setDisplayName(name);
                List<String> lore = getLore();
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
            }
            enchant(item);
            return tags(item);
        }

        private Material getMaterial() {
            Object obj = parserMap.getParseFrom().get(KEY_MATERIAL);
            if (obj == null)
                return null;
            try {
                return Material.valueOf(obj.toString().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new ParserException(obj + " is not a valid material.");
            }
        }

        private short getDamage() {
            return parserMap.getShort(KEY_DAMAGE, new ParserException("The damage specified is not a valid material."), (short) 0);
        }

        private int getAmount() {
            return parserMap.getInteger(KEY_QUANTITY, new ParserException("The amount specified is not a valid integer."), 1);
        }

        private String getName() {
            return parserMap.getString(KEY_NAME, null);
        }

        private List<String> getLore() {
            Object obj = parserMap.getParseFrom().get(KEY_LORE);
            if (obj instanceof List<?>) {
                ArrayList<String> lore = new ArrayList<>();
                ((List<?>) obj).forEach(o -> lore.add(parserMap.parseString(o.toString())));
                return lore;
            }
            return null;
        }

        private void enchant(ItemStack item) {
            Object obj = parserMap.getParseFrom().get(KEY_ENCHANTS);
            if (obj instanceof Map<?, ?>) {
                ((Map<?, ?>) obj).forEach((var, val) -> {
                    try {
                        Enchantment enchantment = Enchantment.getByName(var.toString().toUpperCase());
                        if (enchantment != null)
                            item.addEnchantment(enchantment, Short.parseShort(val.toString()));
                        else {
                            StringBuilder builder = new StringBuilder();
                            for (Enchantment value : Enchantment.values())
                                builder.append(value.getName()).append(" ");
                            throw new ParserException(var + " is not a valid enchant. Here is a list of usable enchants: " + builder.toString());
                        }
                    } catch (NumberFormatException ex) {
                        throw new ParserException(val + " is not a valid enchant level.");
                    }
                });
            }
        }

        @SuppressWarnings("unchecked")
        private ItemStack tags(ItemStack item) {
            Object obj = parserMap.getParseFrom().get(KEY_TAGS);
            if (obj instanceof Map<?, ?>) {
                NBTItem nbtItem = new NBTItem(item);
                new MapClimber<>((Map<String, Object>) obj).iterate((node, key, value) -> {
                    String parent = node.getParent();
                    if (parent != null) {
                        NBTCompound nbtCompound = nbtItem.getCompound(node.getParent());
                        if (nbtCompound == null)
                            nbtCompound = nbtItem.addCompound(parent);
                        setNBTCompound(nbtCompound, key, value);
                    } else
                        setNBTCompound(nbtItem, key, value);
                });
                return nbtItem.getItem();
            }
            return item;
        }

        private void setNBTCompound(NBTCompound nbtCompound, String key, Object obj) {
            String objStr = obj.toString();
            if (ConfigIdentifiers.BOOLEAN_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.BOOLEAN_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setByte(key, parserMap.parseByte(objStr));
            } else if (ConfigIdentifiers.LONG_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.LONG_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setLong(key, parserMap.parseLong(objStr));
            } else if (ConfigIdentifiers.INTEGER_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.INTEGER_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setInteger(key, parserMap.parseInteger(objStr));
            } else if (ConfigIdentifiers.SHORT_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.SHORT_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setShort(key, parserMap.parseShort(objStr));
            } else if (ConfigIdentifiers.BYTE_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.BYTE_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setByte(key, parserMap.parseByte(objStr));
            } else if (ConfigIdentifiers.DOUBLE_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.DOUBLE_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setDouble(key, parserMap.parseDouble(objStr));
            } else if (ConfigIdentifiers.FLOAT_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.FLOAT_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setFloat(key, parserMap.parseFloat(objStr));
            } else {
                nbtCompound.setString(key, objStr);
            }

        }


    }

}
