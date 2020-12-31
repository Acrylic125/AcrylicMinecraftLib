package com.acrylic.universal.events;

import co.aikar.timings.lib.MCTiming;
import co.aikar.timings.lib.TimingManager;
import com.acrylic.universal.Universal;
import com.acrylic.universal.interfaces.PluginRegister;
import com.acrylic.universal.interfaces.Timer;
import com.acrylic.universal.reflection.ReflectionUtils;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.CustomTimingsHandler;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface AbstractEventBuilder<T extends Event> extends Listener, Timer, PluginRegister, Cloneable {

    default void handleThenRegister(@NotNull Consumer<T> eventHandler) {
        handle(eventHandler).register();
    }

    default void handleThenRegister(@NotNull Consumer<T> eventHandler, @NotNull JavaPlugin plugin) {
        handle(eventHandler).register(plugin);
    }

    AbstractEventBuilder<T> handle(@NotNull Consumer<T> eventHandler);

    AbstractEventBuilder<T> filter(@Nullable Predicate<T> filter);

    AbstractEventBuilder<T> priority(@NotNull EventPriority eventPriority);

    AbstractEventBuilder<T> ignoreCancel(boolean ignoreCancel);

    AbstractEventBuilder<T> copy(@NotNull AbstractEventBuilder<T> eventBuilder);

    void setRegisteredBefore(boolean b);

    Consumer<T> getHandle();

    Predicate<T> getFilter();

    EventPriority getPriority();

    boolean shouldIgnoreCancel();

    boolean hasRegisteredBefore();

    Class<T> getEventClass();

    AbstractEventBuilder<T> plugin(@NotNull JavaPlugin plugin);

    JavaPlugin getPlugin();

    String getEventName();

    AbstractEventBuilder<T> setEventName(@NotNull String name);

    default void register() {
        JavaPlugin plugin = getPlugin();
        String name = getEventName();
        if (name == null) {
            name = getPlugin().getName() + "-" + getEventClass().getName();
            setEventName(name);
        }
        register(plugin, name);
    }

    @Deprecated
    @Override
    default void register(@NotNull JavaPlugin plugin) {
        String name = getEventName();
        if (name == null) {
            name = getPlugin().getName() + "-" + getEventClass().getName();
            setEventName(name);
        }
        register(plugin, name);
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    default void register(@NotNull JavaPlugin plugin, @NotNull String name) {
        setEventName(name);
        if (hasRegisteredBefore())
            unregister();
        EventExecutor executor = (listener, event) -> {
            try {
                boolean isAsync = event.isAsynchronous();
                MCTiming timing = getTimings();
                if (!isAsync)
                    timing.startTiming();
                Consumer<T> action = getHandle();
                if (action != null && event.getClass().equals(getEventClass())) {
                    T e = (T) event;
                    Predicate<T> filter = getFilter();
                    if (filter == null || filter.test(e))
                        action.accept(e);
                }
                if (!isAsync)
                    timing.stopTiming();
            } catch (Throwable t) {
                throw new EventException(t);
            }
        };
        plugin.getServer().getPluginManager().registerEvent(getEventClass(), this, getPriority(), executor, plugin);
        setRegisteredBefore(true);
    }

    default void unregister() {
        HandlerList handlerList = ReflectionUtils.getField(getEventClass(), "handlers", HandlerList.class);
        if (handlerList != null)
            handlerList.unregister(this);
    }

    @NotNull
    default MCTiming getTimings() {
        return Universal.getTimingManager().of(getEventName());
    }

}
