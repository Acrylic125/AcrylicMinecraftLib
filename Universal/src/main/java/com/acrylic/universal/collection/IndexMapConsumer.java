package com.acrylic.universal.collection;

@FunctionalInterface
public interface IndexMapConsumer<T, K> {

    void accept(int index, T t, K k);

}
