package com.acrylic.universal.threads;

import co.aikar.timings.lib.MCTiming;
import com.acrylic.time.Time;
import com.acrylic.universal.Universal;
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
        return taskType(TaskType.delayedTask(convertTicksToTime(ticks, Time.NANOSECONDS)));
    }

    default T runDelayedTask(long time, Time timeUnit) {
        return taskType(TaskType.delayedTask(time * timeUnit.getNanoScale()));
    }

    default T runRepeatingTask(long delayTicks, long periodTicks) {
        return taskType(TaskType.repeatTask(convertTicksToTime(delayTicks, Time.NANOSECONDS), convertTicksToTime(periodTicks, Time.NANOSECONDS)));
    }

    default T runRepeatingTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit) {
        return taskType(TaskType.repeatTask(delay * delayTimeUnit.getNanoScale(), period * periodTimeUnit.getNanoScale()));
    }

    default T runRepeatingIndexedTask(long delayTicks, long periodTicks, int maxIndex) {
        return taskType(TaskType.indexRepeatingTask(convertTicksToTime(delayTicks, Time.NANOSECONDS), convertTicksToTime(periodTicks, Time.NANOSECONDS), maxIndex));
    }

    default T runRepeatingIndexedTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit, int maxIndex) {
        return taskType(TaskType.indexRepeatingTask(delay * delayTimeUnit.getNanoScale(), period * periodTimeUnit.getNanoScale(), maxIndex));
    }

    T taskType(@NotNull TaskType taskType);

    @Nullable
    TaskType getTaskType();

    T setName(@NotNull String name);

    String getName();

    T plugin(@NotNull JavaPlugin plugin);

    JavaPlugin getPlugin();

    boolean isAsync();

    default void handleThenBuild(@NotNull ExecutedTask<T> action) {
        handle(action).build();
    }

    default void handleThenBuild(@NotNull Runnable action) {
        handle(action).build();
    }

    T handle(@NotNull ExecutedTask<T> action);

    default T handle(@NotNull Runnable runnable) {
        return handle(task -> runnable.run());
    }

    ExecutedTask<T> getHandle();

    void cancel();

    @SuppressWarnings("unchecked")
    @Override
    default void run() {
        MCTiming timing = getTimings();
        try {
            timing.startTiming();
            getHandle().run((T) this);
        } catch (Throwable t) {
            System.out.println("[ SCHEDULER ERROR ]");
            t.printStackTrace();
            System.out.println("[ END OF SCHEDULER ERROR ]");
        } finally {
            timing.stopTiming();
        }
    }

    void build();

    @NotNull
    default MCTiming getTimings() {
        return Universal.getTimingManager().of(getName());
    }

    static long convertTimeToTicks(long time, @NotNull Time timeUnit) {
        return time * timeUnit.getNanoScale() / 50_000_000;
    }

    static long convertTicksToTime(long ticks, @NotNull Time timeUnit) {
        return (ticks * 50_000_000) / timeUnit.getNanoScale();
    }

}
