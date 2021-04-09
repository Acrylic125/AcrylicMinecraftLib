package com.acrylic.universal.gui;

import com.acrylic.universal.MCLib;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.interfaces.Terminable;
import com.acrylic.universal.gui.components.*;
import com.acrylic.universal.gui.items.GUIClickableItem;
import com.acrylic.universal.gui.items.GUIItem;
import com.acrylic.universal.utils.StoppableIterator;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Represents a GUI object.
 *
 * GUIs differ from the {@link com.acrylic.universal.gui.InventoryUI}.
 * 1.
 * GUIs can make use of the InventoryUI to generate and handle
 * inventories for each individual party/parties.
 *
 * 2.
 * GUIs are a lot more powerful in terms of potential as it
 * provides methods to handle click and close events, UIFormats
 * and much more.
 *
 * @see GlobalGUI
 */
public interface GUI
        extends Terminable {

    @Nullable
    GUIComponents<GUIComponent> getAllComponents();
    
    @Nullable
    Consumer<InventoryClickEvent> getOnClickHandler();

    void setOnClickHandler(@Nullable Consumer<InventoryClickEvent> handle);

    default void runClickHandler(InventoryClickEvent event) {
        Consumer<InventoryClickEvent> handler = getOnClickHandler();
        if (handler != null)
            handler.accept(event);
    }

    @Nullable
    Consumer<InventoryCloseEvent> getOnCloseHandler();

    void setOnCloseHandler(@Nullable Consumer<InventoryCloseEvent> handle);

    default void runCloseHandler(InventoryCloseEvent event) {
        Consumer<InventoryCloseEvent> handler = getOnCloseHandler();
        if (handler != null)
            handler.accept(event);
    }

    void openGUIFor(@NotNull Player player);

    default void openGUIFor(@NotNull Player... players) {
        for (Player player : players)
            openGUIFor(player);
    }

    void update();

    default void initialize(@NotNull InventoryDetails inventoryDetails) {
        inventoryDetails.getInventory().clear();
        GUIComponents<GUIComponent> components = getAllComponents();
        if (components != null) {
            for (GUIComponent component : components.getComponents()) {
                if (component.isAllowedToBeAddedToGUI(this) && component.shouldInitializeOnCall())
                    component.applyComponentToInventory(inventoryDetails);
            }
        }
    }

    default void refresh(@NotNull InventoryDetails inventoryDetails) {
        inventoryDetails.getInventory().clear();
        GUIComponents<GUIComponent> components = getAllComponents();
        if (components != null) {
            for (GUIComponent component : components.getComponents()) {
                if (component.isAllowedToBeAddedToGUI(this) && component.shouldRefreshOnCall())
                    component.applyComponentToInventory(inventoryDetails);
            }
        }
    }

    boolean shouldCancelInventoryClickEvent();

    boolean containsInventory(@NotNull Inventory inventory);

    default void runAllClickableButtons(@NotNull InventoryClickEvent event) {
        UIComparableItemInfo.Comparison comparisonInfo = UIComparableItemInfo.getComparableItemInfo().createComparison(event.getCurrentItem());
        if (comparisonInfo != null) {
            GUIComponents<GUIComponent> components = getAllComponents();
            if (components != null) {
                for (GUIComponent component : getAllComponents().getComponents()) {
                    if (component instanceof GUIItemComponent && ((GUIItemComponent) component).findAndRunButton(this, event, comparisonInfo)) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * Iterates through all clickable items in this GUI.
     *
     * @param action What should be done while iterating.
     */
    default void iterateAllClickableItems(@NotNull StoppableIterator<GUIClickableItem> action) {
        GUIComponents<GUIComponent> components = getAllComponents();
        if (components != null) {
            AtomicBoolean found = new AtomicBoolean(false);
            StoppableIterator<GUIItem> wrappedAction =
                    (item) -> {
                        boolean is = item instanceof GUIClickableItem && action.iterateAndShouldStop((GUIClickableItem) item);
                        if (is)
                            found.set(true);
                        return found.get();
                    };
            for (GUIComponent component : components.getComponents()) {
                if (component instanceof GUIItemComponent) {
                    iterateItemComponent((GUIItemComponent) component, wrappedAction);
                    if (found.get())
                        return;
                }
            }
        }
    }

    /**
     * Iterates through a given component be it in this GUI or
     * not within this GUI.
     *
     * This mainly acts as a helper method but it may be used
     * for testing purposes of anonymous components.
     *
     * @param component The component to iterate if it is
     *                  not null.
     * @param action The action.
     */
    default void iterateItemComponent(@NotNull GUIItemComponent component, @NotNull StoppableIterator<GUIItem> action) {
        for (GUIItem item : component.getGUIItems()) {
            if (action.iterateAndShouldStop(item))
                return;
        }
    }

    /**
     * This acts as a utility method to generate a default
     * {@link AbstractEventBuilder}.
     *
     * @param gui The GUI to use this on. In this use case,
     *            it acts as an identifier.
     * @param listenerType The listener type.
     * @param name The name of this listener.
     * @param <E> The Event type.
     * @return The UNREGISTERED listener.
     */
    static <E extends Event> AbstractEventBuilder<E> generateListener(@NotNull GUI gui, @NotNull Class<E> listenerType, @NotNull String name) {
        return EventBuilder.listen(listenerType)
                .priority(EventPriority.HIGHEST)
                .plugin(MCLib.getPlugin())
                .setEventName(name + " @" + gui);
    }

    /**
     * This provides a base listener whereby it does not intervene
     * with the provided listener by the client.
     *
     * Please note that you do not need this for your GUI
     * implementation but it is highly recommended as it provides
     * the base implementation of any clickable features of
     * the GUI.
     *
     * @param gui The GUI.
     * @return The UNREGISTERED listener.
     */
    static AbstractEventBuilder<InventoryClickEvent> generateGeneralGUIClickListener(@NotNull GUI gui) {
        return bindListenerToGUI(gui, generateListener(gui, InventoryClickEvent.class, "General GUI Click Listener")
                .handle(event -> {
                    gui.runClickHandler(event);
                    if (gui.shouldCancelInventoryClickEvent())
                        event.setCancelled(true);
                    gui.runAllClickableButtons(event);
                })
        );
    }

    static AbstractEventBuilder<InventoryCloseEvent> generateGeneralGUICloseListener(@NotNull GUI gui) {
        return bindListenerToGUI(gui, generateListener(gui, InventoryCloseEvent.class, "General GUI Close Listener")
                .handle(gui::runCloseHandler)
        );
    }

    /**
     * This is a helper method to ensure the handling of an
     * event within the GUI itself.
     *
     * @param gui The gui.
     * @param listener The listener (InventoryEvent based).
     * @param <E> The event type.
     * @return The UNREGISTERED listener.
     *
     * @see InventoryEvent
     * This builds off of the given inventory from the event.
     */
    static <E extends InventoryEvent> AbstractEventBuilder<E> bindListenerToGUI(@NotNull GUI gui, AbstractEventBuilder<E> listener) {
        Consumer<E> handle = listener.getHandle();
        return listener.handle(event -> {
            if (gui.containsInventory(event.getInventory()))
                handle.accept(event);
        });
    }

}
