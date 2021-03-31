package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataShort extends EntityMetadataValue {

    private short value;

    public EntityMetadataShort(String key) {
        this(key, (short) 0);
    }

    public EntityMetadataShort(String key, short value) {
        super(key);
        this.value = value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).shortValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a short.");
        }
    }
}
