package com.acrylic.universal.shapes.lines;

import com.acrylic.universal.interfaces.ToAndFrom;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Line extends BaseLine implements ToAndFrom {

    private Vector from;
    private Vector to;
    private double distance = 0;

    public Line(float frequency) {
        super(frequency);
    }

    public Line(@NotNull Location from, @NotNull Location to, float frequency) {
        super(frequency);
        setFrom(from);
        setTo(to);
    }

    public Line(@NotNull Location from, float frequency) {
        super(frequency);
        setFrom(from);
    }

    public Line(@NotNull Location from, float frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
        setFrom(from);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public void setFrom(@NotNull Vector vector) {
        this.from = vector;
        calculate();
    }

    public Vector getFrom() {
        return from;
    }

    @Override
    public void setTo(@NotNull Vector vector) {
        this.to = vector;
        calculate();
    }

    public Vector getTo() {
        return to;
    }

    public void calculate() {
        if (from != null && to != null) {
            distance = from.distance(to);
            setDV(new Vector(to.getX(), to.getY(), to.getZ())
                    .subtract(from)
                    .multiply(1 / (getFrequency() * distance)));
        }
    }

    public double getDistanceFromOrigin() {
        return getIndex() / getFrequency();
    }

    @Override
    public int getFullCycleIndex() {
         return (int) Math.ceil(distance * getFrequency());
    }

}
