package com.acrylic.universal.shapes.spiral;

import com.acrylic.universal.interfaces.ToAndFrom;

public class Spiral
        extends AbstractSpiral
        implements ToAndFrom {

    public Spiral(float radius, int frequency) {
        super(radius, frequency);
    }

    public Spiral(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(radius, frequency, xRot, yRot, zRot);
    }

    @Override
    public float getSpiralFrequency() {
        return getInitialFrequency() + (getFrequencyIncrement() * getIndex());
    }

    @Override
    public float getSpiralRadius() {
        return getInitialRadius() + (getRadiusIncrement() * getIndex());
    }

}
