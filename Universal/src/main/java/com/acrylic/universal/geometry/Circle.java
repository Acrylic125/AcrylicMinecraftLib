package com.acrylic.universal.geometry;

import com.acrylic.universal.math.Orientation3D;
import org.jetbrains.annotations.NotNull;

public class Circle
        implements Geometry, Rotatable {

    

    @NotNull
    @Override
    public GeometryOptions getGeometryOptions() {
        return null;
    }

    @NotNull
    @Override
    public Orientation3D getOrientation() {
        return null;
    }

    @Override
    public void setOrientation(@NotNull Orientation3D orientation) {

    }
}
