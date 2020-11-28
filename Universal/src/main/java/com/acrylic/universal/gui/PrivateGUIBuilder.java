package com.acrylic.universal.gui;

import com.acrylic.universal.Universal;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.gui.buttons.AbstractButtons;
import com.acrylic.universal.gui.exceptions.NoTemplateDefined;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import com.acrylic.universal.text.ChatUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Getter
public class PrivateGUIBuilder extends GUIBuilder {

    private final AbstractInventoryBuilder inventoryBuilder;

    public static PrivateGUIBuilder create(AbstractInventoryBuilder inventoryBuilder) {
        return new PrivateGUIBuilder(inventoryBuilder);
    }

    public PrivateGUIBuilder(AbstractInventoryBuilder inventoryBuilder) {
        this.inventoryBuilder = inventoryBuilder;
    }

    public PrivateGUIBuilder update(Inventory inventory) {
        AbstractGUITemplate template = getTemplate();
        AbstractButtons buttons = getButtons();
        if (template != null)
            template.apply(inventory);
        if (buttons != null)
            applyButtons(inventory, this);
        return this;
    }

    @Override
    public PrivateGUIBuilder template(AbstractGUITemplate template) {
        super.template(template);
        return this;
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
        for (Player viewer : viewers) {
            Inventory inventory = inventoryBuilder.build();
            update(inventory);
            viewer.openInventory(inventory);
        }
        return this;
    }

    @Override
    public PrivateGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryClickEvent> current = eventBuilder.getFilter();
        final String strippedTitle = ChatColor.stripColor(ChatUtils.get(inventoryBuilder.getTitle()));
        Predicate<InventoryClickEvent> filter = (current != null) ?
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle) && current.test(event) :
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle);

        eventBuilder.filter(filter);
        final Consumer<InventoryClickEvent> clickEventConsumer = eventBuilder.getHandle();
        eventBuilder.handle(event -> {
            clickEventConsumer.accept(event);
            if (getButtons() != null) {
                getButtons().handle(event, this);
            }
        });
        super.clickListener(eventBuilder, plugin);
        return this;
    }

    @Override
    public PrivateGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder, JavaPlugin plugin) {
        final Predicate<InventoryCloseEvent> current = eventBuilder.getFilter();
        final String strippedTitle = ChatColor.stripColor(ChatUtils.get(inventoryBuilder.getTitle()));
        Predicate<InventoryCloseEvent> filter = (current != null) ?
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle) && current.test(event) :
                event -> ChatColor.stripColor(event.getView().getTitle()).equals(strippedTitle)
                ;
        eventBuilder.filter(filter);
        super.closeListener(eventBuilder, plugin);
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
