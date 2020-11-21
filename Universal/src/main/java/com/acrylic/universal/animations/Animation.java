package com.acrylic.universal.animations;

public abstract class Animation implements LocationBasedAnimation {

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