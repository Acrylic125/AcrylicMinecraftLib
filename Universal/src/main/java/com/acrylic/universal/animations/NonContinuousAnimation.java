package com.acrylic.universal.animations;

public abstract class NonContinuousAnimation extends Animation {

    public abstract void endCheck();

    public abstract boolean hasEnded();

}
