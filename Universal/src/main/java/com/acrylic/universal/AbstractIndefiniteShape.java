package com.acrylic.universal;

import com.acrylic.universal.shapes.AbstractShape;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

@Setter @Getter
public abstract class AbstractIndefiniteShape extends AbstractShape {

    private int index = 0;
    private int indexIncrement = 1;

    public AbstractIndefiniteShape(float frequency) {
        super(frequency);
    }

    public Location getLocation(@NotNull final Location location) {
        index += indexIncrement;
        return transformPoint(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }

    public void invokeAction(int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        for (int i = 0; i < count; i++) {
            action.accept(i, getLocation(location));
        }
    }

    public void invokeAction(@NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeAction(getFullCycleIndex(), location, action);
    }

    public int getIndex() {
        return index;
    }

    public abstract Location transformPoint(Location location);

    public abstract int getFullCycleIndex();

}
