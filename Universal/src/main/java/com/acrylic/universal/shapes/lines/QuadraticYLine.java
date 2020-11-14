package com.acrylic.universal.shapes.lines;

import com.acrylic.universal.shapes.functional.VectorEquationLineFunction;
import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * f(d) = scalar(d(d - distance)) + constant
 *
 * Where:
 *   distance = |V_to - V_from|
 *   d        = index / frequency = |V_current - V_from|
 */
@Getter
public class QuadraticYLine extends EquationLineExtension {

    public QuadraticYLine(@NotNull Location from, float frequency) {
        super(from, frequency);
    }

    public QuadraticYLine(@NotNull Location from, float frequency, float xRot, float yRot, float zRot) {
        super(from, frequency, xRot, yRot, zRot);
    }

    @Override
    public VectorEquationLineFunction getFunction() {
        return (equationLine, additiveVector) -> {
            double d = getDistanceFromOrigin();
            return additiveVector.setY((getScalar() * (d * (d - getDistance()))) + getConstant());
        };
    }


}
