package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.exceptions.UnsupportedEntityType;
import com.acrylic.universal.entity.ArmorStandInstance;
import com.acrylic.universal.entity.EntityInstance;
import com.acrylic.universal.geometry.circular.Circle;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class HandRotationAnimation extends EntityAnimation {

    private Circle circle;
    private int index = 0;
    private boolean isUpsideDown;

    public HandRotationAnimation(ArmorStandInstance entityInstance) {
        this((EntityInstance) entityInstance);
        entityInstance.setRightArmPose(new EulerAngle(-1.75, 0, 0));
    }

    public HandRotationAnimation(EntityInstance entityInstance) {
        super(entityInstance);
        updateEntity();
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        circle.setLocation(location);
        Location newLoc = circle.getLocationAtIndex(index);
        newLoc.setDirection(location.toVector().subtract(newLoc.toVector()));
        float newYaw = (isUpsideDown) ? newLoc.getYaw() + (45 - (360 / circle.getPointsInOneCycle())) :
                newLoc.getYaw() - (45 - (360 / circle.getPointsInOneCycle()));
        newLoc.setYaw(newYaw);
        index++;
        return newLoc;
    }

    public void updateEntity() {
        isUpsideDown = getEntityInstance().isUpsideDown();
        circle = generateCircleBy(getEntityInstance().getBukkitEntity(), 32);
    }

    public Location getSourceLocation() {
        return circle.getSourceLocation();
    }

    private static Circle generateCircleBy(@NotNull Entity entity, int points) {
        EntityType entityType = entity.getType();
        switch (entityType) {
            case ARMOR_STAND:
                return new Circle((((ArmorStand) entity).isSmall()) ? 0.3f : 0.65f, points);
            case GIANT:
                return new Circle(4f, points);
            default:
                throw new UnsupportedEntityType();
        }
    }

}
