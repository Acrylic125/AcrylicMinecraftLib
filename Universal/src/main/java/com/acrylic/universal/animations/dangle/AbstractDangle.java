package com.acrylic.universal.animations.dangle;

import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.EntityBoundedAnimation;
import com.acrylic.universal.animations.TimedAnimation;
import com.acrylic.universal.shapes.Circle;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@Setter @Getter
public class AbstractDangle
        extends TimedAnimation
        implements EntityBoundedAnimation, Iterable<EntityAnimation> {

    private static final float RADIUS = 3;
    private static final int BASE_FREQUENCY = 10;
    private static final float ODD_RAISE = 0.35f;

    private int timePerRemovalAnimation = 500;
    private int timeToStartRemoving = 10_000;
    private boolean hasEnded = true;

    private final Circle circle = new Circle(RADIUS, BASE_FREQUENCY);
    private final ArrayList<EntityAnimation> animations = new ArrayList<>();

    public void addAnimation(EntityAnimation entityAnimation) {
        animations.add(entityAnimation);
        hasEnded = false;
        setTimeOnCurrent(timePerRemovalAnimation + timeToStartRemoving);
    }

    public void removeAnimation(EntityAnimation entityAnimation) {
        animations.remove(entityAnimation);
    }

    public void clear() {
        animations.clear();
    }

    @Override
    public void update(@NotNull final Entity entity) {
        if (hasEnded())
            return;
        endCheck();
        final Location location = entity.getLocation();
        final int size = animations.size();
        circle.setFrequency(Math.max(size, BASE_FREQUENCY));
        circle.setOrientationYaw(90 + location.getYaw() + ((size % 2 == 0) ? circle.getAnglesBetween() / 2 : 0));
        location.setY(location.getY() + (location.getDirection().getY() * circle.getRadius()));

        int index = (int) -Math.floor(size / 2f);
        for (EntityAnimation animation : animations) {
            animation.teleportWithHolograms(circle.getLocation(location, index), 0f, (index % 2 == 0) ? 0f : ODD_RAISE);
            index++;
        }
    }

    @Override
    public void endCheck() {
        if (hasExpired()) {
            final int size = animations.size();
            if (size > 1 && timePerRemovalAnimation > 0) {
                EntityAnimation animation = animations.get(0);
                animation.delete();
                animations.remove(animation);
                setTimeOnCurrent(timePerRemovalAnimation);
            } else {
                hasEnded = true;
                delete();
            }
        }
    }

    @Override
    public boolean hasEnded() {
        return hasEnded && animations.size() <= 0;
    }

    @Override
    public void delete() {
        forEach(EntityAnimation::delete);
        animations.clear();
    }

    @NotNull
    @Override
    public Iterator<EntityAnimation> iterator() {
        return animations.iterator();
    }

    @Override
    public void forEach(Consumer<? super EntityAnimation> action) {
        animations.forEach(action);
    }

    @Override
    public Spliterator<EntityAnimation> spliterator() {
        return animations.spliterator();
    }
}
