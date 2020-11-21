package com.acrylic.universal.animations;

import com.acrylic.universal.interfaces.Index;

public abstract class IndexedAnimation
        extends NonContinuousAnimation
        implements Index {

    private int index = 0;
    private int indexIncrement = 1;

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getIndexIncrement() {
        return indexIncrement;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void setIndexIncrement(int indexIncrement) {
        this.indexIncrement = indexIncrement;
    }

}
