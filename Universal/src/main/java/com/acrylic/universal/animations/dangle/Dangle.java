package com.acrylic.universal.animations.dangle;

import com.acrylic.universal.animations.Animation;
import com.acrylic.universal.animations.EntityAnimation;
import com.acrylic.universal.animations.EntityBasedAnimation;
import com.acrylic.universal.animations.RunnableAnimation;
import com.acrylic.universal.files.configloader.ConfigValue;
import com.acrylic.universal.files.configloader.Configurable;
import com.acrylic.universal.geometry.circular.Circle;
import com.acrylic.universal.interfaces.Timed;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Configurable(
        filePath = "acrylic.yml",
        plugin = "Acrylic",
        root = "dangle"
)
public class Dangle
        implements Animation, Timed, RunnableAnimation, EntityBasedAnimation {

    @ConfigValue(path = "radius")
    private static float RADIUS = 3;
    @ConfigValue(path = "base-frequency")
    private static int BASE_FREQUENCY = 10;
    @ConfigValue(path = "odd-raise")
    private static float ODD_RAISE = 0.35f;
    @ConfigValue(path = "time-per-remove")
    private static int TIME_PER_REMOVAL = 500;
    @ConfigValue(path = "time-to-start-removing")
    private static int TIME_TO_START_REMOVING = 10_000;

    private final Circle circle;
    private final List<EntityAnimation> entityAnimations;
    private final float frequency;
    private Entity entity;
    private Scheduler<?> scheduler;
    private int timePerRemovalAnimation = TIME_PER_REMOVAL;
    private int timeToStartRemoving = TIME_TO_START_REMOVING;
    private long time = System.currentTimeMillis();

    public Dangle(@NotNull Entity entity, float radius, int frequency, @NotNull List<EntityAnimation> entityAnimations) {
        this.entity = entity;
        this.circle = new Circle(radius, frequency);
        this.frequency = frequency;
        this.entityAnimations = entityAnimations;
        this.scheduler = Scheduler.sync().runRepeatingTask(1, 1);
    }

    public Dangle(@NotNull Entity entity, float radius, int frequency) {
        this(entity, radius, frequency, new ArrayList<>());
    }

    public Dangle(@NotNull Entity entity, @NotNull List<EntityAnimation> entityAnimations) {
        this(entity, RADIUS, BASE_FREQUENCY, entityAnimations);
    }

    public Dangle(@NotNull Entity entity) {
        this(entity, new ArrayList<>());
    }

    public void addAnimation(@NotNull EntityAnimation entityAnimation) {
        entityAnimations.add(entityAnimation);
        addTimeToNow(timeToStartRemoving);
    }

    public void removeAnimation(@NotNull EntityAnimation entityAnimation) {
        entityAnimations.remove(entityAnimation);
    }

    public void clear() {
        entityAnimations.clear();
    }

    public int getTimePerRemovalAnimation() {
        return timePerRemovalAnimation;
    }

    public void setTimePerRemovalAnimation(int timePerRemovalAnimation) {
        this.timePerRemovalAnimation = timePerRemovalAnimation;
    }

    public int getTimeToStartRemoving() {
        return timeToStartRemoving;
    }

    public void setTimeToStartRemoving(int timeToStartRemoving) {
        this.timeToStartRemoving = timeToStartRemoving;
    }

    public void update() {
        if (isRunning()) {
            checkForRemoval();
            final Location location = entity.getLocation();
            final int size = entityAnimations.size();
            circle.setPointsInOneCycle((int) Math.max(size, frequency));
            circle.modifyYawOrientation(90 + location.getYaw() + ((size % 2 == 0) ? circle.getAngleBetween() / 2 : 0));
            location.setY(location.getY() + (location.getDirection().getY() * circle.getRadius()));
            circle.setLocation(location);
            int index = (int) -Math.floor(size / 2f);
            for (EntityAnimation animation : entityAnimations) {
                animation.teleportWithHolograms(circle.getLocationAtIndex(index), 0f, (index % 2 == 0) ? 0f : ODD_RAISE);
                index++;
            }
        }
    }

    @Override
    public boolean isRunning() {
        return entityAnimations.size() > 0;
    }

    private void checkForRemoval() {
        if (hasPassedTimed()) {
            final int size = entityAnimations.size();
            if (size > 1 && timePerRemovalAnimation > 0) {
                EntityAnimation animation = entityAnimations.get(0);
                animation.delete();
                entityAnimations.remove(animation);
                addTimeToNow(timePerRemovalAnimation);
            } else
                delete();
        }
    }

    public void setEntity(@NotNull Entity entity) {
        this.entity = entity;
    }

    @NotNull
    @Override
    public Entity getEntity() {
        return this.entity;
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
        update();
    }

    @Override
    public void delete() {
        entityAnimations.forEach(EntityAnimation::delete);
        clear();
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void terminate() {
        delete();
        terminateScheduler();
    }
}
