package com.acrylic.universal.entity.metadata;

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
}
