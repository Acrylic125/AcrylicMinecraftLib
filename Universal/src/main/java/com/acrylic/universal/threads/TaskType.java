package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import com.acrylic.universal.interfaces.Index;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

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
 * Synchronous Scheduler {@link SyncScheduleBuilder} ONLY WORKS
 * WITH TICKS AS THE HIGHEST DEGREEOF ACCURACY OF TIME due to
 * how Minecraft functions.
 *
 * ASynchronous Scheduler {@link AsyncScheduleBuilder} however,
 * can have a highest degree of accuracy of Nanoseconds due to
 * the use of a different {@link ScheduleExecutor}.
 */
public class TaskType {

    private static final TaskType EMPTY = new TaskType();

    public BukkitTask scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
        return Bukkit.getScheduler().runTask(scheduleBuilder.getPlugin(), scheduleBuilder);
    }

    public Future<?> scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {
        return ScheduleExecutor.ASYNC_EXECUTOR.runTask(scheduleBuilder);
    }

    public static TaskType task() {
        return EMPTY;
    }

    public static TaskType delayedTask(long delay) {
        return new DelayedTask(delay);
    }

    public static TaskType repeatTask(long delay, long period) {
        return new RepeatingTask(delay, period);
    }

    public static TaskType indexRepeatingTask(long delay, long period, int maxIndex) {
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
        public BukkitTask scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
            return Bukkit.getScheduler().runTaskLater(scheduleBuilder.getPlugin(), scheduleBuilder, Scheduler.convertTimeToTicks(delay, Time.NANOSECONDS));
        }

        @Override
        public Future<?> scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {
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
        public BukkitTask scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
            return Bukkit.getScheduler().runTaskTimer(scheduleBuilder.getPlugin(), scheduleBuilder, Scheduler.convertTimeToTicks(getDelay(), Time.NANOSECONDS), Scheduler.convertTimeToTicks(getPeriod(), Time.NANOSECONDS));
        }

        @Override
        public Future<?> scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {
            return ScheduleExecutor.ASYNC_EXECUTOR.runTaskTimer(scheduleBuilder, getDelay(), Time.NANOSECONDS, getPeriod(), Time.NANOSECONDS);
        }
    }

    public static class IndexedRepeatingTask extends RepeatingTask implements Index {

        private int index = 0;
        private int maxIndex;
        private int indexIncrement = 1;

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
            return indexIncrement;
        }

        @Override
        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public void setIndexIncrement(int indexIncrement) {
            this.indexIncrement = indexIncrement;
        }

        public void update() {
            increaseIndex();
        }

        public boolean hasReachedIndex() {
            return index > maxIndex;
        }

    }

}
