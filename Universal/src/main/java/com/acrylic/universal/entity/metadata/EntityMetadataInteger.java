package com.acrylic.universal.entity.metadata;

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
}
