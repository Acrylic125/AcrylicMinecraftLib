package com.acrylic.universal.interfaces;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface ToAndFrom {

    void setFrom(@NotNull Vector vector);

    void setTo(@NotNull Vector vector);

    default void setFrom(@NotNull Location location) {
        setFrom(location.toVector());
    }

    default void setTo(@NotNull Location location) {
        setTo(location.toVector());
    }

    default void set(@NotNull Location from, @NotNull Location to) {
        setFrom(from);
        setTo(to);
    }

    default void set(@NotNull Vector from, @NotNull Vector to) {
        setFrom(from);
        setTo(to);
    }
}
