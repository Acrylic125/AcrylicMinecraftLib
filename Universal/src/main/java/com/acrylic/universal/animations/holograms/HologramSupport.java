package com.acrylic.universal.animations.holograms;

import org.jetbrains.annotations.Nullable;

public interface HologramSupport {

    AbstractHolograms getHolograms();

    void setHologram(@Nullable AbstractHolograms abstractHolograms);

    default void disableHologram() {
        setHologram(null);
    }

    default boolean isUsingHolograms() {
        return getHolograms() != null;
    }

}
