package com.acrylic.universal.entity.metadata;

public class EntityMetadataDouble extends EntityMetadataValue {

    private double value;

    public EntityMetadataDouble(String key) {
        this(key, 0);
    }

    public EntityMetadataDouble(String key, double value) {
        super(key);
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}
