package com.acrylic.universal.shapes.lines;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EquationLine extends Line {

    private VectorEquationLineFunction vectorEquationLineFunction;

    public EquationLine(@NotNull Location from, float frequency, VectorEquationLineFunction vectorEquationLineFunction) {
        super(from, frequency);
        this.vectorEquationLineFunction = vectorEquationLineFunction;
    }

    public EquationLine(@NotNull Location from, float frequency, VectorEquationLineFunction vectorEquationLineFunction, float xRot, float yRot, float zRot) {
        super(from, frequency, xRot, yRot, zRot);
        this.vectorEquationLineFunction = vectorEquationLineFunction;
    }

    public void setVectorShapeFunction(VectorEquationLineFunction vectorEquationLineFunction) {
        this.vectorEquationLineFunction = vectorEquationLineFunction;
    }

    @Override
    public Vector getAdditiveVector() {
        return vectorEquationLineFunction.evaluate(this, super.getAdditiveVector());
    }

}
