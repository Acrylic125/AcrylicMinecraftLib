package com.acrylic.universal.shapes.spiral;

public class MultiSpiral extends AbstractSpiral {

    private float angleOffset = 10f;

    public MultiSpiral(float radius, int amountOfSpirals) {
        super(radius, amountOfSpirals);
    }

    public MultiSpiral(float radius, int amountOfSpirals, float xRot, float yRot, float zRot) {
        super(radius, amountOfSpirals, xRot, yRot, zRot);
    }

    public void setAngleOffset(float angle) {
        this.angleOffset = angle;
    }

    public float getAngleOffset() {
        return angleOffset;
    }

    public int getAmountOfSpirals() {
        return (int) getBaseFrequency();
    }

    public int getSpiralIndex() {
        return (int) Math.floor((super.getIndex()) / getBaseFrequency());
    }

    @Override
    public float getOffset() {
        return super.getOffset() + (getSpiralIndex() * angleOffset);
    }

    @Override
    public float getSpiralFrequency() {
        return getBaseFrequency() + (getFrequencyIncrement() * getSpiralIndex());
    }

    @Override
    public float getSpiralRadius() {
        return getBaseRadius() + (getRadiusIncrement() * getSpiralIndex());
    }

}
