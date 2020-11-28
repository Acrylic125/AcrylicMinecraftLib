package com.acrylic.universal.files.fileeditor;

import com.acrylic.universal.files.parsers.ItemStackParser;
import files.editor.Configurable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface BukkitFileEditor extends Configurable {

    void setItem(@NotNull ItemStack item);

    default void setItem(@NotNull ItemStackParser item) {

    }

    ItemStack getItem(@NotNull String s);

    ItemStackParser getItemParser(@NotNull String s);



}
