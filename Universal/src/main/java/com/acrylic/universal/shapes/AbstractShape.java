package com.acrylic.universal.shapes;

public abstract class AbstractShape {

    private float frequency;

    public AbstractShape(float frequency) {
        this.frequency = frequency;
    }

    public float getFrequency() {
        return frequency;
    }

    public float getInitialFrequency() {
        return this.frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

}
