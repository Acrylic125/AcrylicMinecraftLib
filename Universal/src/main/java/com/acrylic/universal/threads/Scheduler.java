package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Scheduler<T extends Scheduler<T>> extends Runnable {

    static SyncScheduleBuilder sync() {
        return new SyncScheduleBuilder();
    }

    static AsyncScheduleBuilder async() {
        return new AsyncScheduleBuilder();
    }

    default T runTask() {
        return taskType(TaskType.task());
    }

    default T runDelayedTask(long ticks) {
        return taskType(TaskType.delayedTask(ticks));
    }

    default T runDelayedTask(long time, Time timeUnit) {
        return runDelayedTask(convertTime(time, timeUnit));
    }

    default T runRepeatingTask(long delayTicks, long periodTicks) {
        return taskType(TaskType.repeatTask(delayTicks, periodTicks));
    }

    default T runRepeatingTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit) {
        return runRepeatingTask(convertTime(delay, delayTimeUnit), convertTime(period, periodTimeUnit));
    }

    default T runRepeatingIndexedTask(long delayTicks, long periodTicks, int maxTicks) {
        return taskType(TaskType.indexRepeatingTask(delayTicks, periodTicks, maxTicks));
    }

    default T runRepeatingIndexedTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit, int maxIndex) {
        return runRepeatingIndexedTask(convertTime(delay, delayTimeUnit), convertTime(period, periodTimeUnit), maxIndex);
    }

    T taskType(@NotNull TaskType taskType);

    @Nullable
    TaskType getTaskType();

    T plugin(@NotNull JavaPlugin plugin);

    JavaPlugin getPlugin();

    boolean isAsync();

    T handle(@NotNull ExecutedTask<T> action);

    ExecutedTask<T> getHandle();

    void cancel();

    @SuppressWarnings("unchecked")
    @Override
    default void run() {
        getHandle().run((T) this);
    }

    void build();

    static long convertTime(long time, @NotNull Time timeUnit) {
        return time * timeUnit.getNanoScale() / 20_000_000;
    }

}
