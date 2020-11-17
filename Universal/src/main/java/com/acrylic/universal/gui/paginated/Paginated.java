package com.acrylic.universal.gui.paginated;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * This is adapted from {@link paginatedcollection.Paginated}, a page system implementation.
 * Due to some incompatibilities, the system has been reimplemented into this
 * interface.
 *
 * @param <T> The type.
 */
public interface Paginated<T> {

    int getMaxElementsPerPage();

    Collection<T> getCollection();

    default ArrayList<T> getPageList(int page) {
        ArrayList<T> list = new ArrayList<>();
        iterate(page, list::add);
        return list;
    }

    default int getPage(int page) {
        return (page < 0) ? 1 : Math.min(page, getMaxPage());
    }

    default int getMaxPage() {
        return (int) Math.ceil((float) getCollection().size() / getMaxElementsPerPage());
    }

    default int getElementsFrom(int page) {
        return (getPage(page) - 1) * getMaxElementsPerPage();
    }

    default int getElementsTo(int page) {
        return Math.min(getPage(page) * getMaxElementsPerPage(), getCollection().size());
    }

    default void iterate(int page, @NotNull Consumer<T> consumer) {
        final int starting = getElementsFrom(page);
        final int ending = getElementsTo(page);
        final Collection<T> subCollectionTemplate = getCollection();
        int index = 0;
        for (T t : subCollectionTemplate) {
            if (index >= starting && index < ending)
                consumer.accept(t);
            index++;
        }
    }


}
