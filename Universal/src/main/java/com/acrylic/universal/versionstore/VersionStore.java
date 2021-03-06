package com.acrylic.universal.versionstore;

import com.acrylic.universal.versionstore.exceptions.WrongVersionException;
import org.bukkit.Bukkit;

//The version.
public class VersionStore {

    private final short version = (short) Integer.parseInt(Bukkit.getVersion().split("\\.")[1]);

    int LEGACY_VERSION = 8;

    public short getVersion() {
        return version;
    }

    public boolean isLegacyVersion() {
        return getVersion() <= LEGACY_VERSION;
    }

}
