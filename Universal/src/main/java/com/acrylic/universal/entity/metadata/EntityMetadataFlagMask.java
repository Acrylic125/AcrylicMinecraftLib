package com.acrylic.universal.entity.metadata;

public class EntityMetadataFlagMask extends EntityMetadataValue {

    private int mask;

    public EntityMetadataFlagMask(String key) {
        this(key, 0x0);
    }

    public EntityMetadataFlagMask(String key, int value) {
        super(key);
        this.mask = value;
    }

    public void setMask(int bit, boolean b) {
        if (b)
            mask |= bit;
        else
            mask = mask & ~bit;
    }

    public boolean isBitInMask(int bit) {
        return (mask & bit) == bit;
    }

    public void setValue(int value) {
        this.mask = value;
    }

    @Override
    public Integer getValue() {
        return mask;
    }
}
