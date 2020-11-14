package com.acrylic.universal.shapes.lines;

import org.bukkit.util.Vector;

@FunctionalInterface
public interface VectorEquationLineFunction {

    Vector evaluate(EquationLine equationLine, Vector additiveVector);

}
