package com.acrylic.universal.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class MapClimber<K, V> {

    private final Map<K, V> map;

    /**
     *
     * @param map The map you want to iterate.
    */
    public MapClimber(@NotNull Map<K, V> map) {
        this.map = map;
    }

    public void iterate(Node.NodeAction<K, V> actionWhenIterated) {
        new Node<K, V>(map).act(actionWhenIterated);
    }

    public static class Node<K, V> {

        private final K parent;
        private final Map<K, V> parentMap;
        private final Map<K, V> map;
        private final int index;

        Node(int index, @Nullable Map<K, V> parentMap, @Nullable K parent, @NotNull Map<K, V> map) {
            this.parent = parent;
            this.parentMap = parentMap;
            this.map = map;
            this.index = index;
        }

        Node(Map<K, V> map) {
            this(0, null, null, map);
        }

        public boolean isFirstNode() {
            return index == 0;
        }

        @SuppressWarnings("unchecked")
        public void act(Node.NodeAction<K, V> actionWhenIterated) {
            map.forEach((key, value) -> {
                if (value instanceof Map<?, ?>) {
                    try {
                        new Node<>(index + 1, map, key, (Map<K, V>) value).act(actionWhenIterated);
                    } catch (ClassCastException ex) {
                        throw new RuntimeException("Map climber can only climb maps of similar types (Key and value).");
                    }
                } else {
                    actionWhenIterated.accept(this, key, value);
                }
            });
        }

        public K getParent() {
            return parent;
        }

        public Map<K, V> getParentMap() {
            return parentMap;
        }

        public Map<K, V> getMap() {
            return map;
        }

        @FunctionalInterface
        public interface NodeAction<K, V> {

            void accept(Node<K, V> node, K key, V value);

        }

    }

}
