package com.acrylic.universal.interfaces;

import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import org.jetbrains.annotations.NotNull;

public interface Runner<T extends TaskType>
        extends Terminable, Runnable {

    @NotNull
    Scheduler<T> getScheduler();

    boolean isRunning();

    void setRunning(boolean running);

    default void start() {
        if (!isRunning()) {
            getScheduler().build();
            setRunning(true);
        }
    }

    @Override
    default void terminate() {
        getScheduler().cancel();
    }
}
