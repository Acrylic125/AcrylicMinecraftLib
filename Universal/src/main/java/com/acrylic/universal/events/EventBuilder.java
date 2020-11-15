package com.acrylic.universal.events;

import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventBuilder<T extends Event> implements AbstractEventBuilder<T> {

    private final Class<T> clazz;
    private Consumer<T> eventHandler;
    private Predicate<T> filter;
    private EventPriority priority = EventPriority.NORMAL;
    private boolean shouldIgnoreCancel = false;

    private long lastClocked = 0;
    private boolean shouldClock = false;

    public static <T extends Event> EventBuilder<T> listen (Class<T> clazz) {
        return new EventBuilder<>(clazz);
    }

    private EventBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public AbstractEventBuilder<T> handle(Consumer<T> eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> filter(Predicate<T> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> priority(EventPriority eventPriority) {
        this.priority = eventPriority;
        return this;
    }

    @Override
    public AbstractEventBuilder<T> ignoreCancel(boolean ignoreCancel) {
        this.shouldIgnoreCancel = ignoreCancel;
        return this;
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
    public Class<T> getEventClass() {
        return clazz;
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
    public long getLastClocked() {
        return lastClocked;
    }

    @Override
    public void clockTime() {
        lastClocked = System.currentTimeMillis();
    }
}
