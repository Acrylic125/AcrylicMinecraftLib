package com.acrylic.universal.gui;

import com.acrylic.universal.Universal;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.gui.exceptions.NoTemplateDefined;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Predicate;

public class PrivateGUIBuilder extends GUIBuilder {

    private final InventoryBuilder inventoryBuilder;

    public PrivateGUIBuilder(InventoryBuilder inventoryBuilder) {
        this.inventoryBuilder = inventoryBuilder;
    }

    @Override
    public PrivateGUIBuilder setItem(int slot, ItemStack item) {
        AbstractGUITemplate template = getTemplate();
        if (template == null)
            throw new NoTemplateDefined();
        template.addGUIItem(slot, item);
        return this;
    }

    @Override
    public PrivateGUIBuilder open(Player... viewers) {
        AbstractGUITemplate template = getTemplate();
        boolean hasTemplate = template != null;
        for (Player viewer : viewers) {
            Inventory inventory = inventoryBuilder.build();
            if (hasTemplate)
                template.apply(inventory);
            viewer.openInventory(inventory);
        }
        return this;
    }

    @Override
    public PrivateGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryClickEvent> current = eventBuilder.getFilter();
        final String strippedTitle = ChatColor.stripColor(inventoryBuilder.getTitle());
        Predicate<InventoryClickEvent> filter = (current != null) ?
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle) && current.test(event) :
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle)
                ;
        eventBuilder.filter(filter);
        super.clickListener(eventBuilder, plugin);
        return this;
    }

    @Override
    public PrivateGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryCloseEvent> current = eventBuilder.getFilter();
        final String strippedTitle = ChatColor.stripColor(inventoryBuilder.getTitle());
        Predicate<InventoryCloseEvent> filter = (current != null) ?
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle) && current.test(event) :
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle)
                ;
        eventBuilder.filter(filter);
        super.closeListener(eventBuilder, plugin);
        return this;
    }

    @Override
    public PrivateGUIBuilder template(AbstractGUITemplate template) {
        super.template(template);
        return this;
    }

    @Override
    public PrivateGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder) {
        return clickListener(eventBuilder, Universal.getPlugin());
    }

    @Override
    public PrivateGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder) {
        return closeListener(eventBuilder, Universal.getPlugin());
    }

    @Override
    public PrivateGUIBuilder removeListenersOnClose(boolean b) {
        super.removeListenersOnClose(b);
        return this;
    }

}
