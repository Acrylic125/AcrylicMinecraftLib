package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The code is adapted from https://github.com/lucko/helper/blob/master/helper/src/main/java/me/lucko/helper/scheduler/HelperAsyncExecutor.java
 */
public class ScheduleExecutor {

    public static final ScheduleExecutor ASYNC_EXECUTOR = new ScheduleExecutor();

    private final ExecutorService taskService;
    private final ScheduledExecutorService timerExecutionService;
    private final Set<ScheduledFuture<?>> tasks = Collections.newSetFromMap(new WeakHashMap<>());

    public ScheduleExecutor() {
        this.taskService = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
                .setDaemon(true)
                .build()
        );
        this.timerExecutionService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder()
                .setDaemon(true)
                .build()
        );
    }

    public Set<ScheduledFuture<?>> getTasks() {
        return tasks;
    }

    public ScheduledFuture<?> addTask(@NotNull ScheduledFuture<?> future) {
        tasks.add(future);
        return future;
    }

    @NotNull
    public Future<?> runTask(@NotNull Runnable runnable) {
        return taskService.submit(runnable);
    }

    @NotNull
    public Future<?> runTaskLater(@NotNull Runnable runnable, long delay, TimeUnit timeUnit) {
        return addTask(timerExecutionService.schedule(new AsyncLockWrapper(runnable), delay, timeUnit));
    }

    @NotNull
    public Future<?> runTaskLater(@NotNull Runnable runnable, long delay, Time timeUnit) {
        return runTaskLater(runnable, delay * timeUnit.getNanoScale(), TimeUnit.NANOSECONDS);
    }

    @NotNull
    public Future<?> runTaskTimer(@NotNull Runnable runnable, long delay, long period, @NotNull TimeUnit timeUnit) {
        return addTask(timerExecutionService.scheduleAtFixedRate(new AsyncLockWrapper(runnable), delay, period, timeUnit));
    }

    @NotNull
    public Future<?> runTaskTimer(@NotNull Runnable runnable, long delay, Time delayTimeUnit, long period, Time periodTimeUnit) {
        return runTaskTimer(runnable, delay * delayTimeUnit.getNanoScale(), period * periodTimeUnit.getNanoScale(), TimeUnit.NANOSECONDS);
    }

    public void removeTask(@NotNull ScheduledFuture<?> future) {
        synchronized (tasks) {
            tasks.remove(future);
        }
    }

    public void clearTasks() {
        synchronized (tasks) {
            for (ScheduledFuture<?> task : tasks)
                task.cancel(false);
            tasks.clear();
        }
    }

    private AsyncLockWrapper wrapRunnable(@NotNull Runnable runnable) {
        return new AsyncLockWrapper(runnable);
    }

    private class AsyncLockWrapper implements Runnable {

        private final Runnable runnable;
        private final Lock lock;
        private boolean isCurrentlyProcessing = false;

        private AsyncLockWrapper(@NotNull Runnable runnable) {
            this.runnable = runnable;
            this.lock = new ReentrantLock();
        }

        @Override
        public void run() {
            if (isCurrentlyProcessing)
                return;
            isCurrentlyProcessing = true;
            ScheduleExecutor.this.taskService.execute(() -> {
                try {
                    lock.lock();
                    runnable.run();
                } finally {
                    lock.unlock();
                    isCurrentlyProcessing = false;
                }
            });
        }
    }



}
