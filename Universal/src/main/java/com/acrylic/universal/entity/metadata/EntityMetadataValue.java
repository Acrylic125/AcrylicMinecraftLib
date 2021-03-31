package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityMetadataValue implements EntityMetadata, DataSerializable {

    private final String key;

    public EntityMetadataValue(String key) {
        this.key = key;
    }

    @Override
    public String getMetadataName() {
        return key;
    }

    public abstract Object getValue();

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put(key, getValue());
        return map;
    }

}
