package com.acrylic.universal.files.parsers;

import com.acrylic.universal.files.fileeditor.FileEditor;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public final class ItemStackParser extends AbstractVariableParser<ItemStack> {

    public ItemStackParser(FileEditor fileEditor) {
        super(fileEditor);
    }

    @Override
    public Map<String, Object> serialize(ItemStack toParse) {
        return null;
    }

    @Override
    public ItemStack parse(Map<String, Object> parseFrom) {
        return null;
    }
}
