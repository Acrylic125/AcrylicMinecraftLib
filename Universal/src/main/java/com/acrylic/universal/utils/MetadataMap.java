package com.acrylic.universal.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

public interface MetadataMap<T extends Metadata> {

    @NotNull
    Map<String, T> getMetadataMap();

    @Nullable
    @SuppressWarnings("all")
    default <E extends T> E getMetadata(@NotNull String key, Class<E> ofType) {
        T metadata = getMetadataMap().get(key);
        return (ofType.isInstance(metadata)) ? (E) metadata : null;
    }

    @Nullable
    default T getMetadataOrDefault(@NotNull String key, @NotNull T defaultValue) {
        return getMetadataMap().getOrDefault(key, defaultValue);
    }

    default void addMetadata(@NotNull T value) {
        addMetadata(value.getMetadataName(), value);
    }

    default void addMetadata(@NotNull T... values) {
        for (T value : values)
            addMetadata(value);
    }

    default void addMetadata(@NotNull Collection<T> values) {
        for (T value : values)
            addMetadata(value);
    }

    default void addMetadata(@NotNull String key, @NotNull T value) {
        getMetadataMap().put(key, value);
    }

    default void iterateMetadata(@NotNull BiConsumer<String, T> action) {
        getMetadataMap().forEach(action);
    }

    default void clear() {
        getMetadataMap().clear();
    }

}
