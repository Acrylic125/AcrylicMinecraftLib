package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataByte extends EntityMetadataValue {

    private byte value;

    public EntityMetadataByte(String key) {
        this(key, (byte) 0);
    }

    public EntityMetadataByte(String key, byte value) {
        super(key);
        this.value = value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).byteValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a byte.");
        }
    }

}
