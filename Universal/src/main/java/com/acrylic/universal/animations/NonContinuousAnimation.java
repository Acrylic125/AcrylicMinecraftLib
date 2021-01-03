package com.acrylic.universal.animations;

import com.acrylic.universal.interfaces.Deletable;

public abstract class NonContinuousAnimation implements Deletable {

    public abstract void endCheck();

    public abstract boolean hasEnded();

}
