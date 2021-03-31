package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataFloat extends EntityMetadataValue {

    private float value;

    public EntityMetadataFloat(String key) {
        this(key, 0f);
    }

    public EntityMetadataFloat(String key, float value) {
        super(key);
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Number) {
            setValue(((Number) obj).floatValue());
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a float.");
        }
    }
}
