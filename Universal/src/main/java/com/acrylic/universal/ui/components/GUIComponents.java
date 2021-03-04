package com.acrylic.universal.ui.components;

import com.acrylic.universal.utils.StoppableIterator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public final class GUIComponents<T extends GUIComponent> {

    private Collection<T> components;

    public GUIComponents() {
        this(new ArrayList<>());
    }

    public GUIComponents(@NotNull Collection<T> components) {
        this.components = components;
    }

    @NotNull
    public Collection<T> getComponents() {
        return components;
    }

    public GUIComponents<T> setComponents(@NotNull Collection<T> components) {
        this.components = components;
        return this;
    }

    public GUIComponents<T> addComponent(@NotNull T guiComponent) {
        this.components.add(guiComponent);
        return this;
    }

    public GUIComponents<T> removeComponent(@NotNull T guiComponent) {
        this.components.add(guiComponent);
        return this;
    }

    public GUIComponents<T> clear() {
        this.components.clear();
        return this;
    }

    public void iterateComponentsByClass(@NotNull Class<? extends T> clazz, @NotNull StoppableIterator<T> action) {
        iterateComponents(t -> clazz.equals(t.getClass()), action);
    }

    public void iterateComponentsByID(@NotNull Object id, @NotNull StoppableIterator<T> action) {
        iterateComponents(t -> id.equals(t.getComponentID()), action);
    }

    public void iterateComponents(@NotNull Predicate<T> filter, @NotNull StoppableIterator<T> action) {
        for (T component : components) {
            if (filter.test(component) && action.iterateAndShouldStop(component))
                break;
        }
    }

}
