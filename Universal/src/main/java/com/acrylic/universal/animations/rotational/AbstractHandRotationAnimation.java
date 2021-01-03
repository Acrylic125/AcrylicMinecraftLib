package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.animations.exceptions.UnsupportedEntityType;
import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.shapes.Circle;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractHandRotationAnimation extends EntityAnimation {

    private final Circle circle;
    private final float offsetRadii;

    public AbstractHandRotationAnimation(AbstractArmorStandAnimator entityAnimator) {
        this((EntityAnimator) entityAnimator);
        entityAnimator.rightArmPose(new EulerAngle(-1.75, 0, 0));
    }

    public AbstractHandRotationAnimation(EntityAnimator entityAnimator) {
        super(entityAnimator);
        float[] properties = getEntityProperties(getEntityAnimator().getBukkitEntity());
        circle = new Circle(properties[0], 32);
        this.offsetRadii = properties[1];
    }

    @Override
    public Location getLocation(@NotNull Location location) {
        Location newLoc = circle.getLocation(location);
        newLoc.setDirection(location.toVector().subtract(newLoc.toVector()));
        newLoc.add(offsetRadii * circle.getLastSin(), 0, -offsetRadii * circle.getLastCos());  //Offset Vector.
        return newLoc;
    }

    private static float[] getEntityProperties(Entity entity) {
        EntityType entityType = entity.getType();
        switch (entityType) {
            case ARMOR_STAND:
                return new float[]{(((ArmorStand) entity).isSmall()) ? 0.25f : 0.5f, -0.4f};
            case GIANT:
                return new float[]{3.5f, 2f};
            default:
                throw new UnsupportedEntityType();
        }
    }

}
