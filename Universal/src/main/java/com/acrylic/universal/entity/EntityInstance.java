package com.acrylic.universal.entity;

import com.acrylic.universal.interfaces.Deletable;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface EntityInstance extends Deletable {

    String UPSIDE_DOWN_NAME = "Dinnerbone";
    String RAINBOW_SHEEP_NAME = "Jeb_";

    default void asAnimator() {
        setName(null);
    }

    Entity getBukkitEntity();

    void setName(String name);

    void setNameVisible(boolean nameVisible);

    default void upsideDown() {
        setName(UPSIDE_DOWN_NAME);
    }

    default boolean isUpsideDown() {
        String name = getBukkitEntity().getCustomName();
        return name != null && name.equals(UPSIDE_DOWN_NAME);
    }

    void teleport(@NotNull Location location);

    default void teleport(@NotNull World world, double x, double y, double z, float yaw, float pitch) {
        teleport(new Location(world, x, y, z, yaw, pitch));
    }

    default void teleport(@NotNull World world, double x, double y, double z) {
        teleport(new Location(world, x, y, z));
    }

    default void setVelocity(double x, double y, double z) {
        setVelocity(new Vector(x, y, z));
    }

    void setVelocity(@NotNull Vector velocity);

    default boolean isValid() {
        return getBukkitEntity().isValid();
    }

    @SuppressWarnings("unchecked")
    static <T extends Entity> T spawnEntity(@NotNull Location location, EntityType entity) {
        World world = location.getWorld();
        assert world != null;
        return (T) world.spawnEntity(location, entity);
    }

    @Override
    default void delete() {
        getBukkitEntity().remove();
    }
}
