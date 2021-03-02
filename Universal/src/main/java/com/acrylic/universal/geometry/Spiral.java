package com.acrylic.universal.geometry;

public class Spiral extends Circular {

    private float radianIncrement = 0, radiusIncrement = 0.1f;

    public Spiral() {
        super(1, 1);
    }

    public Spiral(float radius, int spirals) {
        super(radius, spirals);
    }

    public float getAmountOfSpirals() {
        return super.getPoints();
    }

    public void setAmountOfSpirals(int spirals) {
        super.setPoints(spirals);
    }

    private double calculateSpiralCycle(int index) {
        return Math.floor(index / getAmountOfSpirals());
    }

    private float calculateSpiralIndex(int index) {
        return index % getAmountOfSpirals();
    }

    @Override
    protected float calculateRadius(int index) {
        return (float) (super.getRadius() + (radiusIncrement * calculateSpiralCycle(index)));
    }

    @Override
    protected float calculateRadians(int index) {
        return (float) (super.getRadianOffset() + (getRadianBetween() * calculateSpiralIndex(index)) + (radianIncrement * calculateSpiralCycle(index)));
    }

    public float getRadianIncrement() {
        return radianIncrement;
    }

    public void setAngleIncrement(float angleIncrement) {
        this.radianIncrement = (float) Math.toRadians(angleIncrement);
    }

    public float getRadiusIncrement() {
        return radiusIncrement;
    }

    public void setRadiusIncrement(float radiusIncrement) {
        this.radiusIncrement = radiusIncrement;
    }
}
