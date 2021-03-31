package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataDouble extends EntityMetadataValue {

    private double value;

    public EntityMetadataDouble(String key) {
        this(key, 0);
    }

    public EntityMetadataDouble(String key, double value) {
        super(key);
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).doubleValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a double.");
        }
    }
}
