package com.acrylic.universal.shapes.spiral;

import com.acrylic.universal.interfaces.ToAndFrom;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Spiral extends AbstractSpiral implements ToAndFrom {

    public Spiral(float radius, int frequency) {
        super(radius, frequency);
    }

    public Spiral(float radius, int frequency, float xRot, float yRot, float zRot) {
        super(radius, frequency, xRot, yRot, zRot);
    }

    @Override
    public float getSpiralFrequency() {
        return super.getRadius() + (getFrequency() * getIndex());
    }

    @Override
    public float getSpiralRadius() {
        return super.getRadius() + (getRadiusIncrement() * getIndex());
    }

}
