package com.acrylic.universal.threads;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class SyncScheduler<R extends TaskType> extends AbstractScheduler<R> {

    private int id = -1;

    public SyncScheduler(@NotNull R taskType) {
        super(taskType);
    }

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
