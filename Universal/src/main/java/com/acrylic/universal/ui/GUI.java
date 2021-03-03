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
import java.util.function.Predicate;

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

    default void iterateAllClickableItems(@NotNull StoppableIterator<GUIClickableItem> action) {
        StoppableIterator<T> wrappedAction =
                (item) -> item instanceof GUIClickableItem && action.iterateAndShouldStop((GUIClickableItem) item);
        iterateItemComponentIfPresent(getStaticComponent(), wrappedAction);
        iterateItemComponentIfPresent(getUIFormat(), wrappedAction);
    }

    default void iterateItemComponentIfPresent(@Nullable GUIItemComponent<T> component, @NotNull StoppableIterator<T> action) {
        if (component != null) {
            for (T item : component.getGUIItems()) {
                if (action.iterateAndShouldStop(item))
                    return;
            }
        }
    }

    static <T extends GUIItem, E extends Event> AbstractEventBuilder<E> generateListener(@NotNull GUI<T> gui, @NotNull Class<E> listenerType, @NotNull String name) {
        return EventBuilder.listen(listenerType)
                .priority(EventPriority.HIGHEST)
                .plugin(Universal.getPlugin())
                .setEventName(name + " @" + gui);
    }

    static <T extends GUIItem> AbstractEventBuilder<InventoryClickEvent> generateGeneralGUIClickListener(@NotNull GUI<T> gui) {
        return bindListenerToGUI(gui, generateListener(gui, InventoryClickEvent.class, "General GUI Click Listener")
                .handle(event -> {
                    if (gui.shouldCancelInventoryClickEvent())
                        event.setCancelled(true);
                    ItemStack clicked = event.getCurrentItem();
                    UIComparableItemInfo.Item itemInfo = UIComparableItemInfo.getComparableItemInfo().createComparison(clicked);
                    gui.iterateAllClickableItems(guiClickableItem -> guiClickableItem.doesItemMatchWithThis(itemInfo));
                })
        );
    }

    static <T extends GUIItem, E extends InventoryEvent> AbstractEventBuilder<E> bindListenerToGUI(@NotNull GUI<T> gui, AbstractEventBuilder<E> listener) {
        Consumer<E> handle = listener.getHandle();
        return listener.handle(event -> {
            if (gui.containsInventory(event.getInventory()))
                handle.accept(event);
        });
    }

}
