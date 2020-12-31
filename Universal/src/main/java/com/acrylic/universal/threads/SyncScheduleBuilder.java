package com.acrylic.universal.threads;

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
        String name = getName();
        if (name == null) {
            name = generateName();
            setName(name);
        }
        id = getTaskType().scheduleSync(this).getTaskId();
    }
}
