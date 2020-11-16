package com.acrylic.universal.gui;

import com.acrylic.universal.Universal;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class GlobalGUIBuilder extends GUIBuilder {

    private Inventory inventory;

    public static GlobalGUIBuilder create(Inventory inventory) {
        return new GlobalGUIBuilder(inventory);
    }

    public static GlobalGUIBuilder create(InventoryBuilder inventoryBuilder) {
        return new GlobalGUIBuilder(inventoryBuilder);
    }

    public GlobalGUIBuilder(Inventory inventory) {
        this.inventory = inventory;
    }

    public GlobalGUIBuilder(InventoryBuilder inventoryBuilder) {
        this(inventoryBuilder.build());
    }

    public GlobalGUIBuilder inventory(@NotNull Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public GlobalGUIBuilder update() {
        AbstractGUITemplate template = getTemplate();
        if (template != null) {
            template.apply(inventory);
        }
        return this;
    }

    @Override
    public GlobalGUIBuilder setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    @Override
    public GlobalGUIBuilder open(Player... viewers) {
        for (Player viewer : viewers) {
            viewer.openInventory(inventory);
        }
        return this;
    }

    @Override
    public GlobalGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryClickEvent> current = eventBuilder.getFilter();
        Predicate<InventoryClickEvent> filter = (current != null) ?
                event -> event.getInventory().equals(inventory) && current.test(event) :
                event -> event.getInventory().equals(inventory)
                ;
        eventBuilder.filter(filter);

        return (GlobalGUIBuilder) super.clickListener(eventBuilder, plugin);
    }

    @Override
    public GlobalGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryCloseEvent> current = eventBuilder.getFilter();
        Predicate<InventoryCloseEvent> filter = (current != null) ?
                event -> event.getInventory().equals(inventory) && current.test(event) :
                event -> event.getInventory().equals(inventory)
                ;
        eventBuilder.filter(filter);

        return (GlobalGUIBuilder) super.closeListener(eventBuilder, plugin);
    }

    @Override
    public GlobalGUIBuilder template(AbstractGUITemplate template) {
        return (GlobalGUIBuilder) super.template(template);
    }

    @Override
    public GlobalGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder) {
        return clickListener(eventBuilder, Universal.getPlugin());
    }

    @Override
    public GlobalGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder) {
        return closeListener(eventBuilder, Universal.getPlugin());
    }

    @Override
    public GlobalGUIBuilder removeListenersOnClose(boolean b) {
        return (GlobalGUIBuilder) super.removeListenersOnClose(b);
    }

}
