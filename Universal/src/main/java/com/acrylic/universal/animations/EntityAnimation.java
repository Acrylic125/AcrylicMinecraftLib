package com.acrylic.universal.animations;

import com.acrylic.universal.animations.holograms.AbstractHolograms;
import com.acrylic.universal.animations.holograms.HologramSupport;
import com.acrylic.universal.entity.EntityInstance;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class EntityAnimation implements Animation, HologramSupport {

    private final EntityInstance entityInstance;
    private AbstractHolograms holograms;
    private float offsetHeight = 0;

    public EntityAnimation(@NotNull EntityInstance entityInstance) {
        this.entityInstance = entityInstance;
    }

    public float getOffsetHeight() {
        return offsetHeight;
    }

    public void setOffsetHeight(float offsetHeight) {
        this.offsetHeight = offsetHeight;
    }

    public void teleportMainEntity(@NotNull Location location, float offsetHeight) {
        getEntityInstance().teleport(getLocation(new Location(location.getWorld(), location.getX(), location.getY() + this.offsetHeight + offsetHeight, location.getZ(), location.getYaw(), location.getPitch())));
    }

    public void teleportMainEntity(@NotNull Location location) {
        teleportMainEntity(location, 0f);
    }

    public void teleportHolograms(@NotNull Location location, float offsetHeight) {
        if (isUsingHolograms())
            holograms.teleport(location, offsetHeight);
    }

    public void teleportHolograms(@NotNull Location location) {
        teleportHolograms(location, 0f);
    }

    public void teleportWithHolograms(@NotNull Location location, float offsetHeightMain, float offsetHeightHologram) {
        teleportMainEntity(location, offsetHeightMain);
        teleportHolograms(location, offsetHeightHologram);
    }

    public void teleportWithHolograms(@NotNull Location location) {
        teleportWithHolograms(location, 0f, 0f);
    }

    public abstract Location getLocation(Location location);

    @Override
    public void setHologram(@Nullable AbstractHolograms abstractHolograms) {
        this.holograms = abstractHolograms;
    }

    @Nullable
    @Override
    public AbstractHolograms getHolograms() {
        return holograms;
    }

    @NotNull
    public EntityInstance getEntityInstance() {
        return entityInstance;
    }

    @Override
    public void delete() {
        if (isUsingHolograms())
            holograms.delete();
        entityInstance.delete();
    }

    @Override
    public boolean isRunning() {
        return entityInstance.isValid();
    }

    @Override
    public void terminate() {
        delete();
    }

}
