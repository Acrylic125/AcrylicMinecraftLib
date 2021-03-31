package com.acrylic.universal.entity.metadata;

import com.acrylic.universal.utils.MetadataMap;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EntityMetadataMap implements MetadataMap<EntityMetadata> {

    private final Map<String, EntityMetadata> map;

    public EntityMetadataMap() {
        this(new HashMap<>());
    }

    public EntityMetadataMap(@NotNull Map<String, EntityMetadata> map) {
        this.map = map;
    }

    @NotNull
    @Override
    public Map<String, EntityMetadata> getMetadataMap() {
        return null;
    }
}
