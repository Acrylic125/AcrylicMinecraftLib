package com.acrylic.universal.shapes;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
public class Line extends AbstractVectorShape {

    private Vector dV;
    private Vector from;
    private Vector to;

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
        calculateDV();
    }

    public void setTo(@NotNull Location location) {
        this.to = location.toVector();
        calculateDV();
    }

    public void calculateDV() {
        if (from != null && to != null) {
            dV = new Vector(to.getX(), to.getY(), to.getZ())
                    .subtract(from)
                    .multiply(1 / (getFrequency() * from.distance(to)));
        }
    }

    @Override
    public Vector getAdditiveVector() {
        int index = getIndex();
        return (super.shouldReuse()) ?
                getReusableVector().setX(dV.getX() * index).setY(dV.getY() * index).setZ(dV.getZ() * index) :
                new Vector(dV.getX() * index, dV.getY() * index, dV.getZ() * index);
    }
}
