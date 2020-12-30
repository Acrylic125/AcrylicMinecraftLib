package com.acrylic.universal.shapes.spiral;

import com.acrylic.universal.interfaces.ToAndFrom;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.lines.Line;
import com.acrylic.universal.utils.LocationConverter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSpiral
        extends Circle
        implements ToAndFrom {

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

    public void setTimeLine(@Nullable Line timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        if (usingTimeLine())
            location = timeLine.getLocation(location);
        return super.getLocation(location);
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
