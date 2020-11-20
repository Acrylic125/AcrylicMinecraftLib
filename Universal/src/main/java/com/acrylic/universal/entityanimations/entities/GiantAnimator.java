package com.acrylic.universal.entityanimations.entities;

import com.acrylic.universal.entityanimations.EntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;

public class GiantAnimator implements AbstractGiantAnimator {

    private final Giant bukkitEntity;

    public GiantAnimator(Location location) {
        this((Giant) EntityAnimator.spawnEntity(location, EntityType.GIANT));
    }

    public GiantAnimator(Giant bukkitEntity) {
        this.bukkitEntity = bukkitEntity;
    }

    @Override
    public Giant getBukkitEntity() {
        return bukkitEntity;
    }

}
