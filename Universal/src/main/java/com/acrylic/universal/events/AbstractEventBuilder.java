package com.acrylic.universal.events;

import com.acrylic.universal.Universal;
import com.acrylic.universal.timer.Timer;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.CustomTimingsHandler;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface AbstractEventBuilder<T extends Event> extends Listener, Timer {

    AbstractEventBuilder<T> handle(Consumer<T> eventHandler);

    AbstractEventBuilder<T> filter(Predicate<T> filter);

    AbstractEventBuilder<T> priority(EventPriority eventPriority);

    AbstractEventBuilder<T> name(String name);

    AbstractEventBuilder<T> ignoreCancel(boolean ignoreCancel);

    Consumer<T> getHandle();

    Predicate<T> getFilter();

    EventPriority getPriority();

    String getName();

    boolean shouldIgnoreCancel();

    Class<T> getEventClass();

    default AbstractEventBuilder<T> name() {
        return name("Plugin: " + UUID.randomUUID() + " Event: Event" + "::" + UUID.randomUUID() + "(" + UUID.randomUUID() + ")");
    }

    default void register() {
        register(Universal.getPlugin());
    }

    @SuppressWarnings("unchecked")
    default void register(JavaPlugin plugin) {
        String name = getName();
        if (name == null) {
            name();
            name = getName();
        }
        final CustomTimingsHandler timings = new CustomTimingsHandler(name); // Spigot
        EventExecutor executor = (listener, event) -> {
            try {
                boolean isAsync = event.isAsynchronous();
                if (!isAsync)
                    timings.startTiming();
                //
                Consumer<T> action = getHandle();
                if (action != null) {
                    T e = (T) event;
                    Predicate<T> filter = getFilter();
                    if (filter == null || filter.test(e)) {
                        action.accept(e);
                    }
                }
                //
                if (!isAsync)
                    timings.stopTiming();
            } catch (Throwable t) {
                throw new EventException(t);
            }
        };
        plugin.getServer().getPluginManager().registerEvent(getEventClass(), this, getPriority(), executor, plugin);
    }

}
