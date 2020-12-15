package com.acrylic.universal.threads;

public class AsyncScheduleBuilder extends AbstractScheduleBuilder<AsyncScheduleBuilder> {

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void build() {

    }
}
