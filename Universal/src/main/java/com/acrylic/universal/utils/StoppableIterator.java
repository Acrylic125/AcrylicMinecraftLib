package com.acrylic.universal.utils;

@FunctionalInterface
public interface StoppableIterator<T> {

    boolean iterateAndShouldStop(T t);

}
