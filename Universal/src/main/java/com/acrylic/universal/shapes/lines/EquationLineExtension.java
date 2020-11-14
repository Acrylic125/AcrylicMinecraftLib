package com.acrylic.universal.shapes.lines;

import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class EquationLineExtension extends EquationLine {

    private float scalar = 1;
    private float constant = 0;

    public EquationLineExtension(@NotNull Location from, float frequency) {
        super(from, frequency, null);
        update();
    }

    public EquationLineExtension(@NotNull Location from, float frequency, float xRot, float yRot, float zRot) {
        super(from, frequency, null, xRot, yRot, zRot);
        update();
    }

    public EquationLineExtension setScalar(float scalar) {
        this.scalar = scalar;
        return this;
    }

    public EquationLineExtension setConstant(float constant) {
        this.constant = constant;
        return this;
    }

    public void update() {
        setVectorShapeFunction(getFunction());
    }

    public abstract VectorEquationLineFunction getFunction();

}
