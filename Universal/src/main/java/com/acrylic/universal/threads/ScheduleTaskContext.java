package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import org.jetbrains.annotations.NotNull;

public final class ScheduleTaskContext {

    private final boolean isAsync;

    public ScheduleTaskContext(boolean isAsync) {
        this.isAsync = isAsync;
    }

    public Scheduler<TaskType> runTask() {
        return taskType(TaskType.task());
    }

    public Scheduler<TaskType.DelayedTask> runDelayedTask(long ticks) {
        return taskType(TaskType.delayedTask(convertTicksToTime(ticks, Time.NANOSECONDS)));
    }

    public Scheduler<TaskType.DelayedTask> runDelayedTask(long time, Time timeUnit) {
        return taskType(TaskType.delayedTask(time * timeUnit.getNanoScale()));
    }

    public Scheduler<TaskType.RepeatingTask> runRepeatingTask(long delayTicks, long periodTicks) {
        return taskType(TaskType.repeatTask(convertTicksToTime(delayTicks, Time.NANOSECONDS), convertTicksToTime(periodTicks, Time.NANOSECONDS)));
    }

    public Scheduler<TaskType.RepeatingTask> runRepeatingTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit) {
        return taskType(TaskType.repeatTask(delay * delayTimeUnit.getNanoScale(), period * periodTimeUnit.getNanoScale()));
    }

    public Scheduler<TaskType.IndexedRepeatingTask> runRepeatingIndexedTask(long delayTicks, long periodTicks, int maxIndex) {
        return taskType(TaskType.indexRepeatingTask(convertTicksToTime(delayTicks, Time.NANOSECONDS), convertTicksToTime(periodTicks, Time.NANOSECONDS), maxIndex));
    }

    public Scheduler<TaskType.IndexedRepeatingTask> runRepeatingIndexedTask(long delay, Time delayTimeUnit, long period, Time periodTimeUnit, int maxIndex) {
        return taskType(TaskType.indexRepeatingTask(delay * delayTimeUnit.getNanoScale(), period * periodTimeUnit.getNanoScale(), maxIndex));
    }

    public <R extends TaskType> Scheduler<R> taskType(@NotNull R taskType) {
        return (isAsync) ? new AsyncScheduler<R>(taskType) : new SyncScheduler<R>(taskType);
    }

    public static long convertTimeToTicks(long time, @NotNull Time timeUnit) {
        return time * timeUnit.getNanoScale() / 50_000_000;
    }

    public static long convertTicksToTime(long ticks, @NotNull Time timeUnit) {
        return (ticks * 50_000_000) / timeUnit.getNanoScale();
    }

}
