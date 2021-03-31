package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataInteger extends EntityMetadataValue {

    private int value;

    public EntityMetadataInteger(String key) {
        this(key, 0);
    }

    public EntityMetadataInteger(String key, int value) {
        super(key);
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).intValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not an integer.");
        }
    }
}
