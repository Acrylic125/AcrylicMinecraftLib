package com.acrylic.universal.utils;

/**
 * A stoppable iterator is an iterator that can be halted
 * at any point of iteration. Just return true to stop
 * further iterations.
 *
 * @param <T> The type.
 */
@FunctionalInterface
public interface StoppableIterator<T> {

    boolean iterateAndShouldStop(T t);

}
