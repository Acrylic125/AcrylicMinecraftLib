package com.acrylic.universal.geometry;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IndexedRotatableGeometry
        extends Rotatable, IndexedGeometry {

    @Nullable
    Vector getReusableVector();

    default boolean isReusingVector() {
        return getReusableVector() != null;
    }

    @NotNull
    Vector getDeltaVector(int index);

    @NotNull
    @Override
    default Location getLocationAtIndex(int index) {
        Vector vector = getDeltaVector(index);
        getOrientation().transform(vector);
        return getSourceLocation().clone().add(vector);
    }
}
