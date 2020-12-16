package com.acrylic.universal.threads;

import com.acrylic.time.Time;
import org.bukkit.Bukkit;

public class SyncScheduleBuilder extends AbstractScheduleBuilder<SyncScheduleBuilder> {

    private int id = -1;

    public int getId() {
        return id;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(id);
    }

    @Override
    public void build() {
        id = getTaskType().scheduleSync(this).getTaskId();
    }
}
