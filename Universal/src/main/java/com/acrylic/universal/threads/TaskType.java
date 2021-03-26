package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import com.acrylic.universal.interfaces.Index;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * The TaskType of a scheduler describes how the task
 * should run.
 *
 * By default, if the task type is empty/not defined,
 * the scheduler will just execute the runnable.
 *
 * If the Task Type is {@link DelayedTask} then the runnable will
 * run delay time after.
 *
 * @see DelayedTask for more info.
 *
 * If the Task Type is {@link RepeatingTask} then the runnable will
 * run delay time after, every period time.
 *
 * @see RepeatingTask for more info.
 *
 * Synchronous Scheduler {@link SyncScheduler} ONLY WORKS
 * WITH TICKS AS THE HIGHEST DEGREEOF ACCURACY OF TIME due to
 * how Minecraft functions.
 *
 * ASynchronous Scheduler {@link AsyncScheduler} however,
 * can have a highest degree of accuracy of Nanoseconds due to
 * the use of a different {@link ScheduleExecutor}.
 */
public abstract class TaskType {

    public abstract BukkitTask scheduleSync(@NotNull SyncScheduler<?> scheduleBuilder);

    public abstract Future<?> scheduleASync(@NotNull AsyncScheduler<?> scheduleBuilder);

    public static SingleRunTask task() {
        return new SingleRunTask();
    }

    public static SingleRunTask task(@NotNull ExecutorService executorService) {
        return new SingleRunTask(executorService);
    }

    public static DelayedTask delayedTask(long delay) {
        return new DelayedTask(delay);
    }

    public static RepeatingTask repeatTask(long delay, long period) {
        return new RepeatingTask(delay, period);
    }

    public static IndexedRepeatingTask indexRepeatingTask(long delay, long period, int maxIndex) {
        return new IndexedRepeatingTask(delay, period, maxIndex);
    }

    /**
     * HOW IT WORKS:
     *
     * x ns delay,
     *
     * after x nanoseconds, the runnable gets executed.
     *
     * Representation:
     * x ns -> RUN -> END
     */
    public static class DelayedTask extends TaskType {

        private long delay;

        DelayedTask(long delay) {
            this.delay = delay;
        }

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        @Override
        public BukkitTask scheduleSync(@NotNull SyncScheduler<?> scheduleBuilder) {
            return Bukkit.getScheduler().runTaskLater(scheduleBuilder.getPlugin(), scheduleBuilder, ScheduleTaskContext.convertTimeToTicks(delay, Time.NANOSECONDS));
        }

        @Override
        public Future<?> scheduleASync(@NotNull AsyncScheduler<?> scheduleBuilder) {
            return ScheduleExecutor.ASYNC_EXECUTOR.runTaskLater(scheduleBuilder, delay, Time.NANOSECONDS);
        }
    }

    /**
     * HOW IT WORKS:
     *
     * x ns delay
     * y ns period
     *
     * After x nanoseconds, the first iteration of the
     * runnable gets executed. Subsequent runnable executions
     * gets executed y nanoseconds after each iteration.
     *
     * Representation:
     * x ns -> RUN -> y ns -> RUN -> y ns .... etc...
     */
    public static class RepeatingTask extends DelayedTask {

        private long period;

        RepeatingTask(long delay, long period) {
            super(delay);
            this.period = period;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }

        @Override
        public BukkitTask scheduleSync(@NotNull SyncScheduler<?> scheduleBuilder) {
            return Bukkit.getScheduler().runTaskTimer(scheduleBuilder.getPlugin(), scheduleBuilder, ScheduleTaskContext.convertTimeToTicks(getDelay(), Time.NANOSECONDS), ScheduleTaskContext.convertTimeToTicks(getPeriod(), Time.NANOSECONDS));
        }

        @Override
        public Future<?> scheduleASync(@NotNull AsyncScheduler<?> scheduleBuilder) {
            return ScheduleExecutor.ASYNC_EXECUTOR.runTaskTimer(scheduleBuilder, getDelay(), Time.NANOSECONDS, getPeriod(), Time.NANOSECONDS);
        }
    }

    public static class IndexedRepeatingTask extends RepeatingTask implements Index {

        private int index = 0;
        private int maxIndex;

        IndexedRepeatingTask(long delay, long period, int maxIndex) {
            super(delay, period);
            this.maxIndex = maxIndex;
        }

        public int getMaxIndex() {
            return maxIndex;
        }

        public void setMaxIndex(int maxIndex) {
            this.maxIndex = maxIndex;
        }

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public int getIndexIncrement() {
            return 1;
        }

        @Override
        public void setIndex(int index) {
            this.index = index;
        }

        public void update() {
            increaseIndex();
        }

        public boolean hasReachedIndex() {
            return index >= maxIndex;
        }

    }

    public static class SingleRunTask extends TaskType {

        private final ExecutorService executorService;

        SingleRunTask() {
            this.executorService = ScheduleExecutor.ASYNC_EXECUTOR.getDefaultExecutionerService();
        }

        SingleRunTask(ExecutorService executorService) {
            this.executorService = executorService;
        }

        public BukkitTask scheduleSync(@NotNull SyncScheduler<?> scheduleBuilder) {
            return Bukkit.getScheduler().runTask(scheduleBuilder.getPlugin(), scheduleBuilder);
        }

        public Future<?> scheduleASync(@NotNull AsyncScheduler<?> scheduleBuilder) {
            return ScheduleExecutor.ASYNC_EXECUTOR.runTask(executorService, scheduleBuilder);
        }

    }

}
