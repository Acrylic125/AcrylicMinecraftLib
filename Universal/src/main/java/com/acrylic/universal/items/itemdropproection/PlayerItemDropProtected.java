package com.acrylic.universal.items.itemdropproection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class PlayerItemDropProtected
        implements ItemDropProtected {

    private final Collection<UUID> allowedEntities;
    private final Item itemEntity;

    public PlayerItemDropProtected(@NotNull Item itemEntity, @NotNull Collection<UUID> allowedPlayers) {
        this.itemEntity = itemEntity;
        this.allowedEntities = allowedPlayers;
    }

    public PlayerItemDropProtected(@NotNull Item itemEntity) {
        this.itemEntity = itemEntity;
        this.allowedEntities = new HashSet<>();
    }

    public PlayerItemDropProtected(@NotNull Item itemEntity, Player... allowedPlayers) {
        this(itemEntity);
        for (Player player : allowedPlayers)
            this.allowedEntities.add(player.getUniqueId());
    }

    public PlayerItemDropProtected(@NotNull Item itemEntity, @NotNull Player allowedPlayer) {
        this(itemEntity);
        this.allowedEntities.add(allowedPlayer.getUniqueId());
    }

    public void addEntity(@NotNull Entity... entity) {
        for (Entity e : entity)
            addEntity(e);
    }

    public void addEntity(@NotNull Entity entity) {
        addEntity(entity.getUniqueId());
    }

    public void addEntity(@NotNull UUID uuid) {
        allowedEntities.add(uuid);
    }

    public void removeEntity(@NotNull Entity entity) {
        removeEntity(entity.getUniqueId());
    }

    public void removeEntity(@NotNull UUID uuid) {
        allowedEntities.remove(uuid);
    }

    @NotNull
    public Collection<UUID> getAllowedEntities() {
        return allowedEntities;
    }

    @Override
    public Item getItemEntity() {
        return itemEntity;
    }

    @Override
    public boolean isAllowedToPickup(@NotNull Entity entity) {
        return allowedEntities.contains(entity.getUniqueId());
    }

    @Override
    public boolean canRemoveFromMap() {
        return !itemEntity.isValid();
    }
}
