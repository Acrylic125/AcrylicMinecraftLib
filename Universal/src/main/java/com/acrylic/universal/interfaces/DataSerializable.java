package com.acrylic.universal.interfaces;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface DataSerializable extends ConfigurationSerializable {

    void deserialize(@NotNull Map<String, Object> map);

    static void throwCannotBeMappedException(Map<String, Object> map, String reason) {
        throw new IllegalArgumentException("The provided map, " + map + " cannot be mapped to this metadata. Reason: " + reason);
    }

}
