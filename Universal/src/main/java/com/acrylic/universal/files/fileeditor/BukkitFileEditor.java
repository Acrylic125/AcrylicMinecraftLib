package com.acrylic.universal.files.fileeditor;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface BukkitFileEditor extends FileEditor {

    ItemStack getItem(@NotNull String s);

    void setItem(@NotNull ItemStack item);

}
