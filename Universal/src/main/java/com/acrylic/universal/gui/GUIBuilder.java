package com.acrylic.universal.gui;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.buttons.AbstractButtons;
import com.acrylic.universal.gui.buttons.Buttons;
import com.acrylic.universal.gui.buttons.GUIButtonImp;
import com.acrylic.universal.gui.templates.AbstractGUITemplate;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GUIBuilder implements AbstractGUIBuilder, GUIButtonImp {

    private AbstractEventBuilder<InventoryClickEvent> inventoryClickListener;
    private AbstractEventBuilder<InventoryCloseEvent> closeListener;
    private AbstractEventBuilder<InventoryCloseEvent> removeListeners;
    private AbstractGUITemplate template;
    private AbstractButtons buttons = new Buttons();

    public void setButtons(AbstractButtons buttons) {
        this.buttons = buttons;
    }

    @Override
    public AbstractButtons getButtons() {
        return buttons;
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
    public AbstractGUIBuilder template(AbstractGUITemplate template) {
        this.template = template;
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
    public AbstractGUITemplate getTemplate() {
        return template;
    }

    @Override
    public AbstractGUIBuilder removeListenersOnClose(boolean b) {
        if (b && removeListeners == null) {
            removeListeners = EventBuilder
                    .listen(InventoryCloseEvent.class)
                    .handle(inventoryCloseEvent -> {
                        removeListeners();
                    });
        } else if (!b && removeListeners != null) {
            removeListeners.unregister();
            removeListeners = null;
        }
        return this;
    }
}
