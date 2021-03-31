package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataLong extends EntityMetadataValue {

    private long value;

    public EntityMetadataLong(String key) {
        this(key, 0);
    }

    public EntityMetadataLong(String key, long value) {
        super(key);
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).longValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a long.");
        }
    }
}
