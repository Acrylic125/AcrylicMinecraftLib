package com.acrylic.universal.entity.metadata;

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
}
