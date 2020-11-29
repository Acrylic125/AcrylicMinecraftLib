package com.acrylic.universal.javamaps;

@FunctionalInterface
public interface IndexMapConsumer<T, K> {

    void accept(int index, T t, K k);

}
