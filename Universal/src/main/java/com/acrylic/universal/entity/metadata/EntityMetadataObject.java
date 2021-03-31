package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataObject extends EntityMetadataValue {

    private Object value;

    public EntityMetadataObject(String key) {
        this(key, null);
    }

    public EntityMetadataObject(String key, Object value) {
        super(key);
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        setValue(obj);
    }
}
