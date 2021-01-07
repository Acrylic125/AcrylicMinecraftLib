package com.acrylic.universal.threads;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public class AsyncScheduler<R extends TaskType>
        extends AbstractScheduler<R> {

    private Future<?> future;

    public AsyncScheduler(@NotNull R taskType) {
        super(taskType);
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void cancel() {
        if (future != null) {
            future.cancel(false);
            if (future instanceof ScheduledFuture)
                ScheduleExecutor.ASYNC_EXECUTOR.removeTask((ScheduledFuture<?>) future);
        }
    }

    @Override
    public void build() {
        String name = getName();
        if (name == null) {
            name = generateName();
            setName(name);
        }
        future = getTaskType().scheduleASync(this);
    }
}
