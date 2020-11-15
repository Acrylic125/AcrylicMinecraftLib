package com.acrylic.universal.shapes.definiteshape;

import com.acrylic.universal.shapes.AbstractShape;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@Setter @Getter
public abstract class AbstractDefiniteShape extends AbstractShape {

    public AbstractDefiniteShape(float frequency) {
        super(frequency);
    }

    public abstract void invokeAction(@NotNull final Location location, @NotNull final Consumer<Location> action);

}
