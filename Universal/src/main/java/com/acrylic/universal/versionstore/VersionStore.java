package com.acrylic.universal.versionstore;

import org.bukkit.Bukkit;

public class VersionStore implements AbstractVersionStore {

    private final short version = (short) Integer.parseInt(Bukkit.getVersion().split("\\.")[1]);

    @Override
    public short getVersion() {
        return version;
    }
}
