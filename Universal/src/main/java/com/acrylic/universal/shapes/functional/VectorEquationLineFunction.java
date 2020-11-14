package com.acrylic.universal.shapes.functional;

import com.acrylic.universal.shapes.lines.EquationLine;
import org.bukkit.util.Vector;

@FunctionalInterface
public interface VectorEquationLineFunction {

    Vector evaluate(EquationLine equationLine, Vector additiveVector);

}
