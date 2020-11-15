package com.acrylic.universal.gui;

import com.acrylic.universal.events.AbstractEventBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIBuilder implements AbstractGUIBuilder {

    private AbstractEventBuilder<InventoryClickEvent> inventoryClickListener;
    private AbstractEventBuilder<InventoryCloseEvent> closeListener;
    private Inventory inventory;

    public static GUIBuilder create(Inventory inventory) {
        return (GUIBuilder) new GUIBuilder().inventory(inventory);
    }

    @Override
    public AbstractGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder, JavaPlugin plugin) {
        eventBuilder.register(plugin);
        this.inventoryClickListener = eventBuilder;
        return this;
    }

    @Override
    public AbstractGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder, JavaPlugin plugin) {
        eventBuilder.register(plugin);
        this.closeListener = eventBuilder;
        return this;
    }

    @Override
    public AbstractGUIBuilder inventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    @Override
    public AbstractEventBuilder<InventoryClickEvent> getClickListener() {
        return inventoryClickListener;
    }

    @Override
    public AbstractEventBuilder<InventoryCloseEvent> getCloseListener() {
        return closeListener;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public AbstractGUIBuilder setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

}
