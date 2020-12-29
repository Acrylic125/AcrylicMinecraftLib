package com.acrylic.universal.items.itemdropproection;

import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface ItemDropProtectionMap {

    @NotNull
    Map<UUID, ItemDropProtected> getMap();

    void check();

    @Nullable
    default ItemDropProtected getItem(@NotNull Item item) {
        return getItem(item.getUniqueId());
    }

    @Nullable
    default ItemDropProtected getItem(@NotNull UUID uuid) {
        return getMap().get(uuid);
    }

    default void register(@NotNull ItemDropProtected itemDropProtected) {
        getMap().put(itemDropProtected.getItemEntity().getUniqueId(), itemDropProtected);
    }

    default void unregister(@NotNull ItemDropProtected itemDropProtected) {
        unregister(itemDropProtected.getItemEntity().getUniqueId());
    }

    default void unregister(@NotNull UUID id) {
        getMap().remove(id);
    }

}
