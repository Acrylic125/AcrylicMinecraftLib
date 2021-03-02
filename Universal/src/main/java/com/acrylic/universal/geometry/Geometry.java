package com.acrylic.universal.geometry;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a geometric object.
 */
public interface Geometry {

    Location getSourceLocation();

    default void validateSourceLocation() {
        if (getSourceLocation() == null)
            throw new NullPointerException("The source location must be defined!");
    }

}
