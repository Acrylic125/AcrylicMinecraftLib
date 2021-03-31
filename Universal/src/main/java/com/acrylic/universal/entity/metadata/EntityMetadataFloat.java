package com.acrylic.universal.entity.metadata;

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
}
