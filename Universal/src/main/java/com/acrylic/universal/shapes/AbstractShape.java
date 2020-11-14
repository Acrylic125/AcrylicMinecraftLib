package com.acrylic.universal.shapes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

@Setter @Getter
public abstract class AbstractShape {

    private float frequency;
    private int index = 0;
    private int indexIncrement = 1;

    public AbstractShape(float frequency) {
        this.frequency = frequency;
    }

    public Location getLocation(@NotNull final Location location) {
        index += indexIncrement;
        return invoke(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }

    public void invokeAction(int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        for (int i = 0; i < count; i++) {
            action.accept(i, getLocation(location));
        }
    }

    public void invokeAction(@NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeAction(getFullCycleIndex(), location, action);
    }

    public float getFrequency() {
        return frequency;
    }

    public abstract Location invoke(Location location);

    public abstract int getFullCycleIndex();

}
