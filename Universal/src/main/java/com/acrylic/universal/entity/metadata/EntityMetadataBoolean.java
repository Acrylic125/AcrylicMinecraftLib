package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import com.acrylic.universal.utils.Metadata;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataBoolean extends EntityMetadataValue {

    private boolean value;

    public EntityMetadataBoolean(String key) {
        this(key, true);
    }

    public EntityMetadataBoolean(String key, boolean value) {
        super(key);
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        if (obj instanceof Boolean) {
            setValue((Boolean) obj);
        } else {
            DataSerializable.throwCannotBeMappedException(map, obj + " of key, " + getMetadataName() + " is not a boolean.");
        }
    }
}
