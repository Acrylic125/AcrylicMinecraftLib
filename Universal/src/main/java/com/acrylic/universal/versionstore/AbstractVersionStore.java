package com.acrylic.universal.versionstore;

import com.acrylic.universal.versionstore.exceptions.WrongVersionException;

public interface AbstractVersionStore {

    int LEGACY_VERSION = 8;

    short getVersion();

    default boolean isLegacyVersion() {
        return getVersion() <= LEGACY_VERSION;
    }

    default void throwExceptionIfLegacy() {
        throwExceptionIfWrongVersion(LEGACY_VERSION + 1);
    }

    default void throwExceptionIfWrongVersion(int version) {
        if (getVersion() < version)
            throw new WrongVersionException(version);
    }

}
