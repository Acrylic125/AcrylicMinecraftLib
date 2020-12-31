package com.acrylic.universal.files.configloader;

import org.jetbrains.annotations.NotNull;

public final class ObjectPathPair<T> {

    private final T obj;
    private final String[] path;

    public static <T> ObjectPathPair<T> ofRaw(@NotNull T o, @NotNull String path) {
        return new ObjectPathPair<>(o, path.split("/"));
    }

    public static <T> ObjectPathPair<T> of(@NotNull T o, @NotNull String... path) {
        return new ObjectPathPair<>(o, path);
    }

    public ObjectPathPair(@NotNull T o, @NotNull String[] path) {
        this.obj = o;
        this.path = path;
    }

    @NotNull
    public T getObj() {
        return obj;
    }

    @NotNull
    public String[] getPath() {
        return path;
    }
}
