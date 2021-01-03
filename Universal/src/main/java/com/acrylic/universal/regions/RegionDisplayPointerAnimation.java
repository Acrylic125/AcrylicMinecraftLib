package com.acrylic.universal.regions;

import com.acrylic.universal.animations.IndexedAnimation;
import com.acrylic.universal.animations.holograms.Holograms;
import com.acrylic.universal.animations.impl.EntityAnimation;
import com.acrylic.universal.animations.rotational.AbstractHeadRotationAnimation;
import com.acrylic.universal.animations.rotational.HeadRotationAnimation;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityanimations.entities.ArmorStandAnimator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class RegionDisplayPointerAnimation extends IndexedAnimation {

    private final List<AbstractHeadRotationAnimation> animations = new ArrayList<>();
    private int maxIndex = 1000;

    public RegionDisplayPointerAnimation(Region region) {
        int point = 0;
        for (Location corner : region.getCorners()) {
            point++;
            AbstractArmorStandAnimator armorStandAnimator = new ArmorStandAnimator(corner).asAnimator().small(true).marker(true);
            armorStandAnimator.getBukkitEntity().getEquipment().setHelmet(new ItemStack((point % 2 == 0) ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK));
            HeadRotationAnimation animation = new HeadRotationAnimation(armorStandAnimator);
            Location loc = armorStandAnimator.getBukkitEntity().getLocation();
            animation.setHologram(new Holograms());
            animation.getHolograms().addHologram(loc, 1,"&7(" + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + ")", "&b&lPoint #" + point);
            animations.add(animation);
        }
    }

    public void update() {
        increaseIndex();
        endCheck();
        animations.forEach(abstractHeadRotationAnimation -> abstractHeadRotationAnimation.teleportWithHolograms(abstractHeadRotationAnimation.getEntityAnimator().getBukkitEntity().getLocation()));
    }

    @Override
    public void endCheck() {
        if (hasEnded())
            delete();
    }

    @Override
    public boolean hasEnded() {
        return getIndex() > maxIndex;
    }

    @Override
    public void delete() {
        animations.forEach(EntityAnimation::delete);
    }
}
