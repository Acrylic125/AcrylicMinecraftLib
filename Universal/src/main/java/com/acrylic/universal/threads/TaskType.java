package com.acrylic.universal.threads;

import com.acrylic.universal.interfaces.Index;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class TaskType {

    private static final TaskType EMPTY = new TaskType();

    public void scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
        Bukkit.getScheduler().runTask(scheduleBuilder.getPlugin(), scheduleBuilder);
    }

    public void scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {

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
        public void scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
            Bukkit.getScheduler().runTaskLater(scheduleBuilder.getPlugin(), scheduleBuilder, delay);
        }

        @Override
        public void scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {

        }
    }

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
        public void scheduleSync(@NotNull SyncScheduleBuilder scheduleBuilder) {
            Bukkit.getScheduler().runTaskTimer(scheduleBuilder.getPlugin(), scheduleBuilder, getDelay(), getPeriod());
        }

        @Override
        public void scheduleASync(@NotNull AsyncScheduleBuilder scheduleBuilder) {
            super.scheduleASync(scheduleBuilder);
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
