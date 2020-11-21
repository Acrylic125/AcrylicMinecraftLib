package com.acrylic.universal.animations;

import com.acrylic.universal.interfaces.Index;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public interface InvocationLocationAnimation extends Index {

    int getFullCycleIndex();

    Location getLocation(@NotNull final Location location);

    default void invokeAction(int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        for (int i = 0; i < count; i++) {
            action.accept(i, getLocation(location));
        }
    }

    default void invokeAction(@NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeAction(getFullCycleIndex(), location, action);
    }

}
