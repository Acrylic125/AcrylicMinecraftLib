package com.acrylic.universal.interfaces;

public interface Index {

    int getIndex();

    int getIndexIncrement();

    void setIndex(int index);

    default void increaseIndex() {
        setIndex(getIndex() + getIndexIncrement());
    }

}
