package com.acrylic.universal.animations.impl;

import com.acrylic.universal.threads.Scheduler;
import org.jetbrains.annotations.NotNull;

public interface RunnableAnimation extends Runnable {

    @NotNull
    Scheduler<?> getScheduler();

    void setScheduler(@NotNull Scheduler<?> scheduler);

    default void startScheduler() {
        Scheduler<?> scheduler = getScheduler();
        scheduler.handleThenBuild(this);
        setScheduler(scheduler);
    }

    default void terminateScheduler() {
        getScheduler().cancel();
    }

    @Override
    void run();
}
