package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.exceptions.UnsupportedEntityType;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.geometry.circular.Circle;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class HandRotationAnimation extends EntityAnimation {

    private final Circle circle;
    private int index = 0;

    public HandRotationAnimation(AbstractArmorStandAnimator entityAnimator) {
        this((EntityAnimator) entityAnimator);
        entityAnimator.rightArmPose(new EulerAngle(-1.75, 0, 0));
    }

    public HandRotationAnimation(EntityAnimator entityAnimator) {
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

    private static Circle generateCircleBy(@NotNull Entity entity, int points) {
        EntityType entityType = entity.getType();
        switch (entityType) {
            case ARMOR_STAND:
                return new Circle((((ArmorStand) entity).isSmall()) ? 0.3f : 0.65f, points);
            case GIANT:
                return new Circle(3.5f, points);
            default:
                throw new UnsupportedEntityType();
        }
    }

}
