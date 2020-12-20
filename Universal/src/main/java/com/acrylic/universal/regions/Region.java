package com.acrylic.universal.regions;

import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.rotational.AbstractHeadRotationAnimation;
import com.acrylic.universal.animations.rotational.HeadRotationAnimation;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import com.acrylic.universal.regions.exceptions.InvalidLocationException;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * L1 (0, 0, 0)    ------      l3 (1, 0, 0)
 * |                                    |
 * l4 (0, 0, -1)   ------     l2 (1, 0, -1)
 *
 * l3 = (l2_x, y, l1_z)
 * l4 = (l1_x, y, l2_z)
 */
public interface Region {

    @NotNull
    Location getLocation1();

    @NotNull
    Location getLocation2();

    @NotNull
    UUID getId();

    default void validate() throws InvalidLocationException {
        if (!Objects.equals(getLocation1().getWorld(), getLocation2().getWorld()))
            throw new InvalidLocationException("The world of both locations must be the same.");
    }

    @NotNull
    default Location[] getCorners() {
        final Location l1 = getLocation1();
        final Location l2 = getLocation2();
        final World world = l1.getWorld();
        final double x1 = l1.getX(), x2 = l2.getX();
        final double z1 = l1.getZ(), z2 = l2.getZ();
        final double y1 = l1.getY(), y2 = l2.getY();
        final boolean isSameY = y1 == y2;
        final Location[] corners = new Location[(isSameY) ? 4 : 8];

        corners[0] = new Location(world, x1, y1, z1);
        corners[1] = new Location(world, x2, y1, z1);
        corners[2] = new Location(world, x1, y1, z2);
        corners[3] = new Location(world, x2, y1, z2);
        if (isSameY)
            return corners;
        corners[4] = new Location(world, x1, y2, z1);
        corners[5] = new Location(world, x2, y2, z1);
        corners[6] = new Location(world, x1, y2, z2);
        corners[7] = new Location(world, x2, y2, z2);
        return corners;
    }

    default void showMarkers() {
        showMarkers(Universal.getPlugin());
    }

    default void showMarkers(JavaPlugin plugin) {
        RegionDisplayPointerAnimation regionDisplayPointerAnimation = new RegionDisplayPointerAnimation(this);
        Scheduler.sync()
                .runRepeatingTask(1, 1)
                .handle(task -> {
                    if (regionDisplayPointerAnimation.hasEnded())
                        task.cancel();
                    regionDisplayPointerAnimation.update();
                }).build();
    }

    /**
     * d_x = |x1 - x2|
     *
     * Then (|x1 - x| + |x2 - x| <= d_x)
     */
    default boolean isInRegion(@NotNull final Location location) {
        final Location l1 = getLocation1();
        final Location l2 = getLocation2();
        final double x1 = l1.getX(), x2 = l2.getX(), x = location.getX();
        final double z1 = l1.getZ(), z2 = l2.getZ(), z = location.getZ();
        final double y1 = l1.getY(), y2 = l2.getY(), y = location.getY();
        return (Math.abs(x1 - x) + Math.abs(x2 - x)) <= Math.abs(x1 - x2) &&
                (Math.abs(y1 - y) + Math.abs(y2 - y)) <= Math.abs(y1 - y2) &&
                (Math.abs(z1 - z) + Math.abs(z2 - z)) <= Math.abs(z1 - z2);
    }

    default boolean isInRegion(@NotNull Entity entity) {
        return isInRegion(entity.getLocation());
    }

}
