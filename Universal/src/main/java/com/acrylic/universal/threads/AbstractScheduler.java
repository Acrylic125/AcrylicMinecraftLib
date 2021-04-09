package com.acrylic.universal.threads;

import com.acrylic.universal.MCLib;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractScheduler<R extends TaskType>
        implements Scheduler<R> {

    private final R taskType;
    private JavaPlugin plugin = MCLib.getPlugin();
    private ExecutedTask<R> handle;
    private String name = "Unnamed";

    public AbstractScheduler(@NotNull R taskType) {
        this.taskType = taskType;
    }

    @NotNull
    protected String generateName() {
        return plugin.getName() + "-" + taskType.getClass().getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Scheduler<R> setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    @Override
    public Scheduler<R> plugin(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        return this;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public Scheduler<R> handle(@NotNull ExecutedTask<R> action) {
        this.handle = action;
        return this;
    }

    @Override
    public ExecutedTask<R> getHandle() {
        return handle;
    }

    @NotNull
    @Override
    public R getTaskType() {
        return taskType;
    }
}
