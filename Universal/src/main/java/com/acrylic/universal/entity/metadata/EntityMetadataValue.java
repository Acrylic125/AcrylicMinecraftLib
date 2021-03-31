package com.acrylic.universal.entity.metadata;

public abstract class EntityMetadataValue implements EntityMetadata {

    private final String key;

    public EntityMetadataValue(String key) {
        this.key = key;
    }

    @Override
    public String getMetadataName() {
        return key;
    }

    public abstract Object getValue();
}
