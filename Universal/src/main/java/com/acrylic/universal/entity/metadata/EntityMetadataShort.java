package com.acrylic.universal.entity.metadata;

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
}
