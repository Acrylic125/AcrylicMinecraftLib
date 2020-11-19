package com.acrylic.universal.gui.modules;

import com.acrylic.universal.gui.guiitems.AbstractGUIItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface AbstractGUIModule<T extends AbstractGUIItem>
        extends Iterable<T> {

    ArrayList<T> getList();

    default void removeItem(int slot) {
        T check = getItem(slot);
        if (check != null)
            getList().remove(check);
    }

    default void addItem(@NotNull T guiItem) {
        T check = getItem(guiItem.getSlot());
        ArrayList<T> list = getList();
        if (check != null)
            list.remove(check);
        list.add(guiItem);
    }

    default void addItems(@NotNull T... guiItems) {
        for (T guiItem : guiItems)
            addItem(guiItem);
    }

    default T getItem(int slot) {
        for (T guiItem : getList())
            if (guiItem.getSlot() == slot)
                return guiItem;
        return null;
    }

    @NotNull
    @Override
    default Iterator<T> iterator() {
        return getList().iterator();
    }

    @Override
    default void forEach(Consumer<? super T> action) {
        getList().forEach(action);
    }

    @Override
    default Spliterator<T> spliterator() {
        return getList().spliterator();
    }
}
