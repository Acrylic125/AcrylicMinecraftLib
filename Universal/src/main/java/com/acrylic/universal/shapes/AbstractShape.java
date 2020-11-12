package com.acrylic.universal.shapes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@Setter @Getter
public abstract class AbstractShape {

    private float radius;
    private int amount;
    private int index = 0;

    public AbstractShape(float radius, int amount) {
        this.radius = radius;
        this.amount = amount;
    }

    public Location getLocation(@NotNull final Location location) {
        index++;
        return invoke(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()));
    }

    public void invokeAction(int count, @NotNull final Location location, @NotNull final Consumer<Location> action) {
        for (int i = 0; i < count; i++) {
            action.accept(getLocation(location));
        }
    }

    public abstract Location invoke(Location location);

}
