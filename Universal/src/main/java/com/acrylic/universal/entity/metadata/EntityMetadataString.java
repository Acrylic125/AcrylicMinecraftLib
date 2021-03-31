package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.interfaces.DataSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EntityMetadataString extends EntityMetadataValue {

    private String value;

    public EntityMetadataString(String key) {
        this(key, null);
    }

    public EntityMetadataString(String key, String value) {
        super(key);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        Object obj = map.get(getMetadataName());
        setValue((obj != null) ? obj.toString() : null);
    }

}
