package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.files.parsers.exceptions.ParserException;
import com.acrylic.universal.text.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static class ItemParser {

        private final Map<String, Object> parseFrom;
        private final Map<String, Object> variables;
        private final String[] replaceFrom = new String[1];
        private final String[] replaceTo = new String[1];

        @SuppressWarnings("unchecked")
        public ItemParser(ItemStackParser itemStackParser, Map<String, Object> parseFrom) {
            Object itemMap = parseFrom.get(COMPOUND_ITEM);
            if (!(itemMap instanceof Map<?, ?>))
                throw new ParserException("There is no item to serialize.");
            this.parseFrom = (Map<String, Object>) itemMap;
            this.variables = new HashMap<>();
           /** Map<String, AbstractConfigVariable> map = itemStackParser.getVariableMap();
            this.replaceFrom = new String[map.size()];
            this.replaceTo = new String[map.size()];
            AtomicInteger index = new AtomicInteger(-1);
            map.forEach((var, val) -> {
                int i = index.addAndGet(1);
                Object obj = val.getNumberValue();
                this.replaceFrom[i] = "@" + var;
                Bukkit.broadcastMessage(var);
                this.replaceTo[i] = obj.toString();
                variables.put(var, obj);
            });**/
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
            return item;
        }

        private Material getMaterial() {
            Object obj = parseFrom.get(KEY_MATERIAL);
            if (obj == null)
                return null;
            try {
                return Material.valueOf(obj.toString().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new ParserException("The material specified is not a valid material.");
            }
        }

        private short getDamage() {
            Object obj = parseFrom.get(KEY_DAMAGE);
            if (obj == null)
                return 0;
            try {
                String str = obj.toString();
                if (str.contains("@")) {
                    str = str.replaceFirst("@", "");
                    Object o = variables.get(str);
                    if (o instanceof Number)
                        return ((Number) o).shortValue();
                }
                return Short.parseShort(str);
            } catch (IllegalArgumentException ex) {
                throw new ParserException("The damage specified is not a valid material.");
            }
        }

        private int getAmount() {
            Object obj = parseFrom.get(KEY_QUANTITY);
            if (obj == null)
                return 1;
            try {
                String str = obj.toString();
                if (str.contains("@")) {
                    str = str.replaceFirst("@", "");
                    Object o = variables.get(str);
                    if (o instanceof Number)
                        return ((Number) o).intValue();
                }
                return Integer.parseInt(obj.toString());
            } catch (IllegalArgumentException ex) {
                throw new ParserException("The amount specified is not a valid integer.");
            }
        }

        private String getName() {
            Object obj = parseFrom.get(KEY_NAME);
            if (obj == null)
                return null;
            return ChatUtils.get(ChatUtils.get(ChatUtils.get(StringUtils.replaceEach(obj.toString(), replaceFrom, replaceTo))));
        }

        private List<String> getLore() {
            Object obj = parseFrom.get(KEY_LORE);
            if (obj instanceof List<?>) {
                ArrayList<String> lore = new ArrayList<>();
                ((List<?>) obj).forEach(o -> lore.add(ChatUtils.get(StringUtils.replaceEach(o.toString(), replaceFrom, replaceTo))));
                return lore;
            }
            return null;
        }
    }

}
