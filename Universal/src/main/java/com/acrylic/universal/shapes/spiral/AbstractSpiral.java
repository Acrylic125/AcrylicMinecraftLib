package com.acrylic.universal.shapes.spiral;

import com.acrylic.universal.interfaces.ToAndFrom;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.lines.Line;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSpiral extends Circle implements ToAndFrom {

    private float frequencyIncrement = 0;
    private float radiusIncrement = 0;
    private Line timeLine;

    public AbstractSpiral(float radius, int frequency) {
        super(radius, frequency);
    }

    public AbstractSpiral(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(radius, frequency, xRot, yRot, zRot);
    }

    public boolean usingTimeLine() {
        return timeLine != null;
    }

    public void setShouldUseTimeLine(boolean shouldUseTimeLine) {
        timeLine = (shouldUseTimeLine) ? new Line(getFrequency()) : null;
    }

    public float getFrequencyIncrement() {
        return frequencyIncrement;
    }

    public void setFrequencyIncrement(float frequencyIncrement) {
        this.frequencyIncrement = frequencyIncrement;
    }

    public float getRadiusIncrement() {
        return radiusIncrement;
    }

    public void setRadiusIncrement(float radiusIncrement) {
        this.radiusIncrement = radiusIncrement;
    }

    public Line getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(Line timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        if (usingTimeLine())
            location = timeLine.getLocation(location);
        return super.getLocation(location);
    }

    public float getBaseRadius() {
        return super.getRadius();
    }

    public float getBaseFrequency() {
        return super.getFrequency();
    }

    @Override
    public float getRadius() {
        return getSpiralRadius();
    }

    @Override
    public float getFrequency() {
        return getSpiralFrequency();
    }

    @Override
    public void setFrom(@NotNull Vector vector) {
        timeLine.setFrom(vector);
    }

    @Override
    public void setTo(@NotNull Vector vector) {
        timeLine.setTo(vector);
    }

    @Override
    public int getFullCycleIndex() {
        return (usingTimeLine()) ? timeLine.getFullCycleIndex() : (int) getFrequency();
    }

    public abstract float getSpiralFrequency();

    public abstract float getSpiralRadius();


}
