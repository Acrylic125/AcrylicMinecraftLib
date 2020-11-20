package com.acrylic.universal.interfaces;

public interface Index {

    int getIndex();

    int getIndexIncrement();

    void setIndex(int index);

    void setIndexIncrement(int indexIncrement);

    default void increaseIndex() {
        setIndex(getIndex() + getIndexIncrement());
    }

}
