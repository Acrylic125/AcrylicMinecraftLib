package com.acrylic.universal.entity.metadata;

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
}
