package com.acrylic.universal.regions;

import com.acrylic.universal.animations.holograms.Holograms;
import com.acrylic.universal.animations.Animation;
import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.RunnableAnimation;
import com.acrylic.universal.animations.rotational.HeadRotationAnimation;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import com.acrylic.universal.files.configloader.ConfigValue;
import com.acrylic.universal.files.configloader.Configurable;
import com.acrylic.universal.interfaces.Index;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Configurable(
        filePath = "acrylic.yml",
        plugin = "Acrylic",
        root = {"regions", "display-pointer"}
)
public class RegionDisplayPointerAnimation
        implements Animation, RunnableAnimation, Index {

    @ConfigValue(path = "durations-ticks")
    private static int DURATION_TICKS = 1000;

    private final List<HeadRotationAnimation> animations = new ArrayList<>();
    private int maxIndex = DURATION_TICKS;
    private int index = 0;
    private Scheduler<?> scheduler = Scheduler.sync().runRepeatingTask(1, 1);

    public RegionDisplayPointerAnimation(Region region) {
        int point = 0;
        for (Location corner : region.getCorners()) {
            point++;
            AbstractArmorStandAnimator armorStandAnimator = new ArmorStandAnimator(corner).asAnimator().small(true).marker(true);
            armorStandAnimator.getBukkitEntity().getEquipment().setHelmet(new ItemStack((point % 2 == 0) ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK));
            HeadRotationAnimation animation = new HeadRotationAnimation(armorStandAnimator);
            Location loc = armorStandAnimator.getBukkitEntity().getLocation();
            Holograms holograms = new Holograms();
            animation.setHologram(holograms);
            holograms.addHologram(loc, 1,"&7(" + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + ")", "&b&lPoint #" + point);
            animations.add(animation);
        }
    }

    public void update() {
        increaseIndex();
        endCheck();
        animations.forEach(headRotationAnimation -> headRotationAnimation.teleportWithHolograms(headRotationAnimation.getEntityAnimator().getBukkitEntity().getLocation()));
    }

    private void endCheck() {
        if (hasEnded())
            terminate();
    }

    public boolean hasEnded() {
        return getIndex() > maxIndex;
    }

    @Override
    public void delete() {
        animations.forEach(EntityAnimation::delete);
    }

    @Override
    public boolean isRunning() {
        return !animations.isEmpty();
    }

    @NotNull
    @Override
    public Scheduler<?> getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(@NotNull Scheduler<?> scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        if (isRunning())
            update();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getIndexIncrement() {
        return 1;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void setIndexIncrement(int indexIncrement) {
        throw new IllegalStateException("You may not set the index increment.");
    }

    @Override
    public void terminate() {
        delete();
        terminateScheduler();
    }
}
