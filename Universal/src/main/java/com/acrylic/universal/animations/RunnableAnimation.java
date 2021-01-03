package com.acrylic.universal.animations;

import com.acrylic.universal.Universal;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface RunnableAnimation extends Runnable {

    @NotNull
    Scheduler<?> getScheduler();

    void setScheduler(@NotNull Scheduler<?> scheduler);

    default void startScheduler(@NotNull JavaPlugin plugin) {
        Scheduler<?> scheduler = getScheduler();
        scheduler.plugin(plugin).handleThenBuild(this);
        setScheduler(scheduler);
    }

    default void startScheduler() {
        startScheduler(Universal.getPlugin());
    }

    default void terminateScheduler() {
        getScheduler().cancel();
    }

    @Override
    void run();
}
