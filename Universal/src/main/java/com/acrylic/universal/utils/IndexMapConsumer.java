package com.acrylic.universal.utils;

@FunctionalInterface
public interface IndexMapConsumer<T, K> {

    void accept(int index, T t, K k);

}
