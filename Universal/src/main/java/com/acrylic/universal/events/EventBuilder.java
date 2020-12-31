package com.acrylic.universal.events;

import com.acrylic.universal.Universal;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventBuilder<T extends Event>
        implements AbstractEventBuilder<T> {

    private final Class<T> clazz;
    private Consumer<T> eventHandler;
    private Predicate<T> filter;
    private EventPriority priority = EventPriority.NORMAL;
    private boolean shouldIgnoreCancel = false;
    private String name;
    private JavaPlugin plugin = Universal.getPlugin();

    private long lastTimed = 0;
    private boolean shouldClock = false;

    private boolean hasRegisteredBefore = false;

    public static <T extends Event> EventBuilder<T> listen (Class<T> clazz) {
        return new EventBuilder<>(clazz);
    }

    public EventBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public AbstractEventBuilder<T> handle(@NotNull Consumer<T> eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> filter(Predicate<T> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> priority(@NotNull EventPriority eventPriority) {
        this.priority = eventPriority;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> ignoreCancel(boolean ignoreCancel) {
        this.shouldIgnoreCancel = ignoreCancel;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> copy(@NotNull AbstractEventBuilder<T> eventBuilder) {
        this.filter = eventBuilder.getFilter();
        this.priority = eventBuilder.getPriority();
        this.shouldIgnoreCancel = eventBuilder.shouldIgnoreCancel();
        this.eventHandler = eventBuilder.getHandle();
        return this;
    }

    @Override
    public void setRegisteredBefore(boolean b) {
        hasRegisteredBefore = b;
    }

    @Override
    public Consumer<T> getHandle() {
        return eventHandler;
    }

    @Override
    public Predicate<T> getFilter() {
        return filter;
    }

    @Override
    public EventPriority getPriority() {
        return priority;
    }

    @Override
    public boolean shouldIgnoreCancel() {
        return shouldIgnoreCancel;
    }

    @Override
    public boolean hasRegisteredBefore() {
        return hasRegisteredBefore;
    }

    @Override
    public Class<T> getEventClass() {
        return clazz;
    }

    @Override
    public AbstractEventBuilder<T> plugin(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public String getEventName() {
        return name;
    }

    @Override
    public AbstractEventBuilder<T> setEventName(@NotNull String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean isTimerActive() {
        return shouldClock;
    }

    @Override
    public EventBuilder<T> setTimerActive(boolean isTimerActive) {
        this.shouldClock = isTimerActive;
        return this;
    }

    @Override
    public long getLastTimed() {
        return lastTimed;
    }

    @Override
    public void setLastTimed(long time) {
        this.lastTimed = time;
    }

    @Override
    public AbstractEventBuilder<T> clone() {
        return new EventBuilder<T>(clazz)
                .filter(filter)
                .priority(priority)
                .ignoreCancel(shouldIgnoreCancel)
                .handle(eventHandler);
    }
}
