package com.acrylic.universal.entityanimations;

import com.acrylic.universal.interfaces.Deletable;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.utils.TeleportationUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAnimator extends Deletable {

    Entity getBukkitEntity();

    float getHeight();

    float getHologramHeight();

    default EntityAnimator name(@Nullable String name) {
        getBukkitEntity().setCustomName((name == null) ? null : ChatUtils.get(name));
        return nameVisible(name != null);
    }

    default EntityAnimator nameVisible(boolean b) {
        getBukkitEntity().setCustomNameVisible(b);
        return this;
    }

    default void teleport(@NotNull Location location) {
        Entity entity = getBukkitEntity();
        if (entity != null)
            TeleportationUtils.tp(entity, location);
    }

    default boolean isValid() {
        Entity entity = getBukkitEntity();
        return entity != null && entity.isValid();
    }

    @Override
    default void delete() {
        if (isValid())
            getBukkitEntity().remove();
    }

    @SuppressWarnings("unchecked")
    static <T extends Entity> T spawnEntity(@NotNull Location location, EntityType entity) {
        World world = location.getWorld();
        assert world != null;
        return (T) world.spawnEntity(location, entity);
    }

}
