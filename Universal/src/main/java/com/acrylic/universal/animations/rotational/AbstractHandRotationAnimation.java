package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.exceptions.UnsupportedEntityType;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractHandRotationAnimation extends EntityAnimation {

    private final HandRotationalCircle circle;
    private int index = 0;

    public AbstractHandRotationAnimation(AbstractArmorStandAnimator entityAnimator) {
        this((EntityAnimator) entityAnimator);
        entityAnimator.rightArmPose(new EulerAngle(-1.75, 0, 0));
    }

    public AbstractHandRotationAnimation(EntityAnimator entityAnimator) {
        super(entityAnimator);
        circle = generateCircleBy(entityAnimator.getBukkitEntity(), 32);
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        circle.setLocation(location);
        Location newLoc = circle.getLocationAtIndex(index);
        newLoc.setDirection(location.toVector().subtract(newLoc.toVector()));
        newLoc.setYaw(newLoc.getYaw() - (45 - (360 / circle.getPointsInOneCycle())));
        index++;
        return newLoc;
    }

    private static HandRotationalCircle generateCircleBy(@NotNull Entity entity, int points) {
        EntityType entityType = entity.getType();
        switch (entityType) {
            case ARMOR_STAND:
                return new HandRotationalCircle((((ArmorStand) entity).isSmall()) ? 0.3f : 0.65f, points);
            case GIANT:
                return new HandRotationalCircle(3.5f, points);
            default:
                throw new UnsupportedEntityType();
        }
    }

}
