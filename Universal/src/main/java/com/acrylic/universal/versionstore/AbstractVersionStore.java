package com.acrylic.universal.versionstore;

public interface AbstractVersionStore {

    short getVersion();

    default boolean isLegacyVersion() {
        return getVersion() <= 8;
    }

}
