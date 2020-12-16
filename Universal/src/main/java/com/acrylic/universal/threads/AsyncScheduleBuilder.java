package com.acrylic.universal.threads;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public class AsyncScheduleBuilder extends AbstractScheduleBuilder<AsyncScheduleBuilder> {

    private Future<?> future;

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
        future = getTaskType().scheduleASync(this);
    }
}
