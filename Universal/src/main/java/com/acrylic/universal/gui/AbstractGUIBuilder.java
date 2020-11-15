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

public interface AbstractGUIBuilder {

    AbstractGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder, JavaPlugin plugin);

    AbstractGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder, JavaPlugin plugin);

    AbstractGUIBuilder template(AbstractGUITemplate template);

    AbstractEventBuilder<InventoryClickEvent> getClickListener();

    AbstractEventBuilder<InventoryCloseEvent> getCloseListener();

    AbstractGUITemplate getTemplate();

    AbstractGUIBuilder setItem(int slot, ItemStack item);

    AbstractGUIBuilder open(Player... viewers);

    AbstractGUIBuilder removeListenersOnClose(boolean b);

    /**
     * You should always remove the listeners if the UI is no
     * longer in use.
     */
    default void removeListeners() {
        AbstractEventBuilder<InventoryClickEvent> clickListener = getClickListener();
        if (clickListener != null)
            clickListener.unregister();
        AbstractEventBuilder<InventoryCloseEvent> closeListener = getCloseListener();
        if (closeListener != null)
            closeListener.unregister();
    }

    /** DO NOT REGISTER THE EVENT! **/
    default AbstractGUIBuilder clickListener(AbstractEventBuilder<InventoryClickEvent> eventBuilder) {
        return clickListener(eventBuilder, Universal.getPlugin());
    }

    /** DO NOT REGISTER THE EVENT! **/
    default AbstractGUIBuilder closeListener(AbstractEventBuilder<InventoryCloseEvent> eventBuilder) {
        return closeListener(eventBuilder, Universal.getPlugin());
    }

}
