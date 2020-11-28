package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.utils.MapClimber;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;

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
        return new ItemParser(this, parseFrom).getItem();
    }

    /**
     * Item Parser.
     */
    private static class ItemParser extends ParserProducer<ItemStack> {

        @SuppressWarnings("unchecked")
        public ItemParser(ItemStackParser itemStackParser, Map<String, Object> parseFrom) {
            super(itemStackParser, parseFrom.get(COMPOUND_ITEM));
        }

        public ItemStack getItem() {
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
            Object obj = getParseFrom().get(KEY_MATERIAL);
            if (obj == null)
                return null;
            try {
                return Material.valueOf(obj.toString().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new ParserException(obj + " is not a valid material.");
            }
        }

        private short getDamage() {
            Object obj = getParseFrom().get(KEY_DAMAGE);
            if (obj == null)
                return 0;
            return getShort(obj.toString(), new ParserException("The damage specified is not a valid material."));
        }

        private int getAmount() {
            Object obj = getParseFrom().get(KEY_QUANTITY);
            if (obj == null)
                return 1;
            return getInteger(obj.toString(), new ParserException("The amount specified is not a valid integer."));
        }

        private String getName() {
            Object obj = getParseFrom().get(KEY_NAME);
            if (obj == null)
                return null;
            return ChatUtils.get(ChatUtils.get(scanStringForVariables(obj.toString())));
        }

        private List<String> getLore() {
            Object obj = getParseFrom().get(KEY_LORE);
            if (obj instanceof List<?>) {
                ArrayList<String> lore = new ArrayList<>();
                ((List<?>) obj).forEach(o -> lore.add(ChatUtils.get(scanStringForVariables(o.toString()))));
                return lore;
            }
            return null;
        }

        private void enchant(ItemStack item) {
            Object obj = getParseFrom().get(KEY_ENCHANTS);
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
            Object obj = getParseFrom().get(KEY_TAGS);
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
                nbtCompound.setByte(key, getByte(objStr));
            } else if (ConfigIdentifiers.LONG_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.LONG_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setLong(key, getLong(objStr));
            } else if (ConfigIdentifiers.INTEGER_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.INTEGER_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setInteger(key, getInteger(objStr));
            } else if (ConfigIdentifiers.SHORT_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.SHORT_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setShort(key, getShort(objStr));
            } else if (ConfigIdentifiers.BYTE_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.BYTE_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setByte(key, getByte(objStr));
            } else if (ConfigIdentifiers.DOUBLE_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.DOUBLE_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setDouble(key, getDouble(objStr));
            } else if (ConfigIdentifiers.FLOAT_PATTERN.matcher(objStr).find()) {
                objStr = ConfigIdentifiers.FLOAT_PATTERN.matcher(objStr).replaceFirst("");
                nbtCompound.setFloat(key, getFloat(objStr));
            } else {
                nbtCompound.setString(key, objStr);
            }

        }


    }

}
