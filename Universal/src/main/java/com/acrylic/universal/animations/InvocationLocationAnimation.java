package com.acrylic.universal.animations;

import com.acrylic.universal.Universal;
import com.acrylic.universal.interfaces.Index;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public interface InvocationLocationAnimation extends Index {

    int getFullCycleIndex();

    Location getLocation(@NotNull final Location location);

    default void invokeAction(int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        for (int i = 0; i < count; i++)
            action.accept(i, getLocation(location));
    }

    default void invokeAction(@NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeAction(getFullCycleIndex(), location, action);
    }

    default void invokeWithScheduler(@NotNull JavaPlugin plugin, int delayTicks, int periodTicks, int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        Scheduler.sync()
                .runRepeatingIndexedTask(delayTicks, periodTicks, count)
                .plugin(plugin)
                .handleThenBuild(task -> {
                    TaskType.IndexedRepeatingTask taskType = ((TaskType.IndexedRepeatingTask) task.getTaskType());
                    taskType.increaseIndex();
                    if (taskType.hasReachedIndex())
                        task.cancel();
                    else
                        action.accept(((TaskType.IndexedRepeatingTask) task.getTaskType()).getIndex(), getLocation(location));
                });
    }

    default void invokeWithScheduler(int delayTicks, int periodTicks, int count, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeWithScheduler(Universal.getPlugin(), delayTicks, periodTicks, count, location, action);
    }

    default void invokeWithScheduler(@NotNull JavaPlugin plugin, int delayTicks, int periodTicks, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeWithScheduler(plugin, delayTicks, periodTicks, getFullCycleIndex(), location, action);
    }

    default void invokeWithScheduler(int delayTicks, int periodTicks, @NotNull final Location location, @NotNull final BiConsumer<Integer, Location> action) {
        invokeWithScheduler(delayTicks, periodTicks, getFullCycleIndex(), location, action);
    }

}
