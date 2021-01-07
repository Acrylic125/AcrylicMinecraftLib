package com.acrylic.universal.threads;

import co.aikar.timings.lib.MCTiming;
import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface Scheduler<R extends TaskType> extends Runnable {

    static ScheduleTaskContext sync() {
        return new ScheduleTaskContext(false);
    }

    static ScheduleTaskContext async() {
        return new ScheduleTaskContext(true);
    }

    @NotNull
    R getTaskType();

    Scheduler<R> setName(@NotNull String name);

    String getName();

    Scheduler<R> plugin(@NotNull JavaPlugin plugin);

    JavaPlugin getPlugin();

    boolean isAsync();

    default void handleThenBuild(@NotNull ExecutedTask<R> action) {
        handle(action).build();
    }

    default void handleThenBuild(@NotNull Runnable action) {
        handle(action).build();
    }

    Scheduler<R> handle(@NotNull ExecutedTask<R> action);

    default Scheduler<R> handle(@NotNull Runnable runnable) {
        return handle(task -> runnable.run());
    }

    ExecutedTask<R> getHandle();

    void cancel();

    @SuppressWarnings("unchecked")
    @Override
    default void run() {
        MCTiming timing = getTimings();
        try {
            timing.startTiming();
            getHandle().run(this);
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

}
