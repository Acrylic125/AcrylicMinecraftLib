package com.acrylic.universal.geometry;

public class Circle
        extends Circular {

    public Circle() {
    }

    /**
     *
     * @param radius The radius.
     * @param points The amount of points determines how many
     *               points there are in 1 full circle cycle.
     */
    public Circle(float radius, int points) {
        super(radius, points);
    }

    public float getPointsInOneCycle() {
        return super.getPoints();
    }

    public void setPointsInOneCycle(int points) {
        super.setPoints(points);
    }

    @Override
    protected float calculateRadius(int index) {
        return getRadius();
    }

    @Override
    protected float calculateRadians(int index) {
        return (index * getRadianBetween()) + getRadianOffset();
    }


}
