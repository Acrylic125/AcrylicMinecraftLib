package com.acrylic.universal.shapes.definiteshape;

import com.acrylic.universal.shapes.lines.Line;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class AbstractAngularLineSketcher extends AbstractDefiniteShape {

    private final Line sketcher;
    private float angle;
    private int lines = 3;

    public AbstractAngularLineSketcher(float frequency, float angle) {
        super(frequency);
        this.angle = angle;
        sketcher = new Line(frequency);
    }

    @Override
    public synchronized void invokeAction(@NotNull Location location, @NotNull Consumer<Location> action) {
        for (int i = 0; i < lines; i++) {

        }
    }


}
