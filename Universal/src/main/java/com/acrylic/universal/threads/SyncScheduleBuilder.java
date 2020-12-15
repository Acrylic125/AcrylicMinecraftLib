package com.acrylic.universal.threads;

import org.bukkit.Bukkit;

public class SyncScheduleBuilder extends AbstractScheduleBuilder<SyncScheduleBuilder> {

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void build() {
        getTaskType().scheduleSync(this);
    }
}
