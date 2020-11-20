package com.acrylic.universal.shapes.lines;

import com.acrylic.universal.shapes.AbstractVectorShape;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.Vector;

@Setter @Getter
public class BaseLine extends AbstractVectorShape {

    private Vector dV;

    public BaseLine(float frequency) {
        super(frequency);
    }

    public BaseLine(float frequency, float xRot, float yRot, float zRot) {
        super(frequency, xRot, yRot, zRot);
    }

    @Override
    public Vector getAdditiveVector() {
        int index = getIndex();
        return (super.shouldReuseVector()) ?
                getReusableVector().setX(dV.getX() * index).setY(dV.getY() * index).setZ(dV.getZ() * index) :
                new Vector(dV.getX() * index, dV.getY() * index, dV.getZ() * index);
    }

    @Override
    public int getFullCycleIndex() {
        return 1;
    }
}
