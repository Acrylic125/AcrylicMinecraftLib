package com.acrylic.universal.shapes.lines;

import com.acrylic.universal.shapes.AbstractVectorShape;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public class Line extends AbstractVectorShape {

    private Vector dV;
    private Vector from;
    private Vector to;
    private double distance = 0;

    public Line(@NotNull Location from, float frequency) {
        super(frequency);
        setFrom(from);
    }

    public Line(@NotNull Location from, float frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
        setFrom(from);
    }

    public void setFrom(@NotNull Location location) {
        this.from = location.toVector();
        calculate();
    }

    public void setTo(@NotNull Location location) {
        this.to = location.toVector();
        calculate();
    }

    public void calculate() {
        if (from != null && to != null) {
            distance = from.distance(to);
            dV = new Vector(to.getX(), to.getY(), to.getZ())
                    .subtract(from)
                    .multiply(1 / (getFrequency() * distance));
        }
    }

    public double getDistanceFromOrigin() {
        return getIndex() / getFrequency();
    }

    @Override
    public Vector getAdditiveVector() {
        int index = getIndex();
        return (super.shouldReuse()) ?
                getReusableVector().setX(dV.getX() * index).setY(dV.getY() * index).setZ(dV.getZ() * index) :
                new Vector(dV.getX() * index, dV.getY() * index, dV.getZ() * index);
    }
}
