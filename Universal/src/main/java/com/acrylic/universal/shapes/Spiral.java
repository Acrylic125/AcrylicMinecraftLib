package com.acrylic.universal.shapes;

import com.acrylic.universal.interfaces.ToAndFrom;
import com.acrylic.universal.shapes.lines.Line;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Setter @Getter
public class Spiral extends Circle implements ToAndFrom {

    private float frequencyIncrement = 0;
    private float radiusIncrement = 0;
    private Line timeLine;

    public Spiral(float radius, int frequency) {
        super(radius, frequency);
    }

    public Spiral(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(radius, frequency, xRot, yRot, zRot);
    }

    public boolean usingTimeLine() {
        return timeLine != null;
    }

    public void setShouldUseTimeLine(boolean shouldUseTimeLine) {
        timeLine = (shouldUseTimeLine) ? new Line(getFrequency()) : null;
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        if (usingTimeLine())
            location = timeLine.getLocation(location);
        return super.getLocation(location);
    }

    @Override
    public float getRadius() {
        return super.getRadius() + (radiusIncrement * getIndex());
    }

    @Override
    public float getFrequency() {
        return super.getFrequency() + (frequencyIncrement * getIndex());
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
    public Vector getAdditiveVector() {
        float radius = getRadius();
        float rad = getRadiansBetween() * getIndex();
        double x = radius * Math.cos(rad);
        double z = radius * Math.sin(rad);
        return (super.shouldReuse()) ?
                getReusableVector().setX(x).setY(0).setZ(z) :
                new Vector(x, 0, z);
    }

    @Override
    public int getFullCycleIndex() {
        return (usingTimeLine()) ? timeLine.getFullCycleIndex() : (int) getFrequency();
    }

}
