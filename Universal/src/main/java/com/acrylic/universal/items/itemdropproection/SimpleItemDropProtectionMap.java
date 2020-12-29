package com.acrylic.universal.items.itemdropproection;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleItemDropProtectionMap implements ItemDropProtectionMap {

    private final Map<UUID, ItemDropProtected> map = new HashMap<>();

    @NotNull
    @Override
    public Map<UUID, ItemDropProtected> getMap() {
        return map;
    }

    @Override
    public void check() {
        map.entrySet().removeIf(entry -> entry.getValue().canRemoveFromMap());
    }
}
