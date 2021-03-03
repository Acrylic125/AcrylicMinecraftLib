package com.acrylic.universal.ui;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.interfaces.Terminable;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUI extends Terminable {

    @Nullable
    AbstractEventBuilder<InventoryClickEvent> getClickListener();

    @Nullable
    AbstractEventBuilder<InventoryCloseEvent> getCloseListener();

    @NotNull
    Inventory getInventory();

    default void create() {

    }

}
