package com.acrylic.universal.interfaces;

import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import org.jetbrains.annotations.NotNull;

public interface Runner<T extends TaskType>
        extends Terminable, Runnable {

    @NotNull
    Scheduler<T> getScheduler();

    boolean isRunning();

    default void start() {
        if (!isRunning())
            getScheduler().build();
    }

    @Override
    default void terminate() {
        getScheduler().cancel();
    }
}
