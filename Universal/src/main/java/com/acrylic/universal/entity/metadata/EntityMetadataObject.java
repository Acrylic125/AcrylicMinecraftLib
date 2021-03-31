package com.acrylic.universal.entity.metadata;

public class EntityMetadataObject extends EntityMetadataValue {

    private Object value;

    public EntityMetadataObject(String key) {
        this(key, null);
    }

    public EntityMetadataObject(String key, Object value) {
        super(key);
        this.value = value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
