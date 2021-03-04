package com.acrylic.universal.geometry;

import org.bukkit.Location;

/**
 * Represents a geometric object.
 */
public interface Geometry {

    Location getSourceLocation();

    default void validateUse() {
        if (getSourceLocation() == null)
            throw new NullPointerException("The source location must be defined!");
    }

}
