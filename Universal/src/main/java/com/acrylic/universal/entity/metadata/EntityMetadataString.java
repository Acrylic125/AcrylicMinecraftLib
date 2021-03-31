package com.acrylic.universal.entity.metadata;

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
}
