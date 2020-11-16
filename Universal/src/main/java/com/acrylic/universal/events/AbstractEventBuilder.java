package com.acrylic.universal.events;

import com.acrylic.universal.interfaces.PluginRegister;
import com.acrylic.universal.interfaces.Timer;
import com.acrylic.universal.reflection.ReflectionUtils;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.CustomTimingsHandler;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface AbstractEventBuilder<T extends Event> extends Listener, Timer, PluginRegister, Cloneable {

    AbstractEventBuilder<T> handle(Consumer<T> eventHandler);

    AbstractEventBuilder<T> filter(Predicate<T> filter);

    AbstractEventBuilder<T> priority(EventPriority eventPriority);

    AbstractEventBuilder<T> ignoreCancel(boolean ignoreCancel);

    AbstractEventBuilder<T> copy(AbstractEventBuilder<T> eventBuilder);

    void setRegisteredBefore(boolean b);

    Consumer<T> getHandle();

    Predicate<T> getFilter();

    EventPriority getPriority();

    boolean shouldIgnoreCancel();

    boolean hasRegisteredBefore();

    Class<T> getEventClass();

    @Override
    @SuppressWarnings("unchecked")
    default void register(JavaPlugin plugin) {
        final CustomTimingsHandler timings = new CustomTimingsHandler("Plugin: " + plugin.getDescription().getFullName() + " Event: " + getClass().getName() + "::" + getEventClass().getName() + "(" + getEventClass().getSimpleName() + ")");
        if (hasRegisteredBefore())
            unregister();
        EventExecutor executor = (listener, event) -> {
            try {
                boolean isAsync = event.isAsynchronous();
                if (!isAsync)
                    timings.startTiming();
                Consumer<T> action = getHandle();
                if (action != null && event.getClass().equals(getEventClass())) {
                    T e = (T) event;
                    Predicate<T> filter = getFilter();
                    if (filter == null || filter.test(e))
                        action.accept(e);
                }
                if (!isAsync)
                    timings.stopTiming();
            } catch (Throwable t) {
                throw new EventException(t);
            }
        };
        plugin.getServer().getPluginManager().registerEvent(getEventClass(), this, getPriority(), executor, plugin);
        setRegisteredBefore(true);
    }

    default void unregister() {
        HandlerList handlerList = ReflectionUtils.getField(getEventClass(), "handlers", HandlerList.class);
        if (handlerList != null) {
            handlerList.unregister(this);
        }
    }

}
