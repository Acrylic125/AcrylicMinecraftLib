package com.acrylic.universal.utils;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationSearcher {

    @Nullable
    Location getLocation(@NotNull Location source);

}
