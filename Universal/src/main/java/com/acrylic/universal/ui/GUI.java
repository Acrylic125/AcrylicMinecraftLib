package com.acrylic.universal.ui;

import com.acrylic.universal.Universal;
import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.interfaces.Terminable;
import com.acrylic.universal.ui.components.GUIItem;
import com.acrylic.universal.ui.components.GUIItemComponent;
import com.acrylic.universal.ui.components.GUIStaticComponent;
import com.acrylic.universal.ui.uibuttons.GUIClickableItem;
import com.acrylic.universal.ui.uiformats.UIFormat;
import com.acrylic.universal.utils.StoppableIterator;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Represents a GUI object.
 *
 * GUIs differ from the {@link com.acrylic.universal.ui.InventoryUI}.
 * 1.
 * GUIs can make use of the InventoryUI to generate and handle
 * inventories for each individual party/parties.
 *
 * 2.
 * GUIs are a lot more powerful in terms of potential as it
 * provides methods to handle click and close events, UIFormats
 * and much more.
 *
 * @param <T> The GUIItem type.
 *
 * @see GlobalGUI
 */
public interface GUI<T extends GUIItem>
        extends Terminable {

    @Nullable
    UIFormat<T> getUIFormat();

    @Nullable
    GUIStaticComponent<T> getStaticComponent();

    @Nullable
    AbstractEventBuilder<InventoryClickEvent> getClickListener();

    @Nullable
    AbstractEventBuilder<InventoryCloseEvent> getCloseListener();

    void openGUIFor(@NotNull Player player);

    default void openGUIFor(@NotNull Player... players) {
        for (Player player : players)
            openGUIFor(player);
    }

    void update();

    boolean shouldCancelInventoryClickEvent();

    boolean containsInventory(@NotNull Inventory inventory);

    /**
     * Iterates through all clickable items in this GUI.
     *
     * @param action What should be done while iterating.
     */
    default void iterateAllClickableItems(@NotNull StoppableIterator<GUIClickableItem> action) {
        StoppableIterator<T> wrappedAction =
                (item) -> item instanceof GUIClickableItem && action.iterateAndShouldStop((GUIClickableItem) item);
        iterateItemComponentIfPresent(getStaticComponent(), wrappedAction);
        iterateItemComponentIfPresent(getUIFormat(), wrappedAction);
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
    default void iterateItemComponentIfPresent(@Nullable GUIItemComponent<T> component, @NotNull StoppableIterator<T> action) {
        if (component != null) {
            for (T item : component.getGUIItems()) {
                if (action.iterateAndShouldStop(item))
                    return;
            }
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
     * @param <T> The GUIItem type of the GUI.
     * @param <E> The Event type.
     * @return The UNREGISTERED listener.
     */
    static <T extends GUIItem, E extends Event> AbstractEventBuilder<E> generateListener(@NotNull GUI<T> gui, @NotNull Class<E> listenerType, @NotNull String name) {
        return EventBuilder.listen(listenerType)
                .priority(EventPriority.HIGHEST)
                .plugin(Universal.getPlugin())
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
     * @param <T> The type of the item of the GUI
     * @return The UNREGISTERED listener.
     */
    static <T extends GUIItem> AbstractEventBuilder<InventoryClickEvent> generateGeneralGUIClickListener(@NotNull GUI<T> gui) {
        return bindListenerToGUI(gui, generateListener(gui, InventoryClickEvent.class, "General GUI Click Listener")
                .handle(event -> {
                    if (gui.shouldCancelInventoryClickEvent())
                        event.setCancelled(true);
                    ItemStack clicked = event.getCurrentItem();
                    UIComparableItemInfo.Comparison comparisonInfo = UIComparableItemInfo.getComparableItemInfo().createComparison(clicked);
                    gui.iterateAllClickableItems(guiClickableItem -> guiClickableItem.doesItemMatchWithThis(comparisonInfo));
                })
        );
    }

    /**
     * This is a helper method to ensure the handling of an
     * event within the GUI itself.
     *
     * @param gui The gui.
     * @param listener The listener (InventoryEvent based).
     * @param <T> The GUIItem type of the GUI.
     * @param <E> The event type.
     * @return The UNREGISTERED listener.
     *
     * @see InventoryEvent
     * This builds off of the given inventory from the event.
     */
    static <T extends GUIItem, E extends InventoryEvent> AbstractEventBuilder<E> bindListenerToGUI(@NotNull GUI<T> gui, AbstractEventBuilder<E> listener) {
        Consumer<E> handle = listener.getHandle();
        return listener.handle(event -> {
            if (gui.containsInventory(event.getInventory()))
                handle.accept(event);
        });
    }

}
