package com.acrylic.universal.shapes.lines;

import com.acrylic.universal.shapes.interfaces.ToAndFrom;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public class Line extends BaseLine implements ToAndFrom {

    private Vector from;
    private Vector to;
    private double distance = 0;

    public Line(float frequency) {
        super(frequency);
    }

    public Line(@NotNull Location from, float frequency) {
        super(frequency);
        setFrom(from);
    }

    public Line(@NotNull Location from, float frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
        setFrom(from);
    }

    @Override
    public void setFrom(@NotNull Vector vector) {
        this.from = vector;
        calculate();
    }

    @Override
    public void setTo(@NotNull Vector vector) {
        this.to = vector;
        calculate();
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

}
