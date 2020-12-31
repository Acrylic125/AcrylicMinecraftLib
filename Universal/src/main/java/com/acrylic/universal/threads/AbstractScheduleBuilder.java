package com.acrylic.universal.threads;

import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unchecked")
public abstract class AbstractScheduleBuilder<T extends Scheduler<T>>
        implements Scheduler<T> {

    private JavaPlugin plugin = Universal.getPlugin();
    private ExecutedTask<T> handle;
    private TaskType taskType = TaskType.task();
    private String name;

    @NotNull
    protected String generateName() {
        return plugin.getName() + "-" + taskType.getClass().getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T setName(@NotNull String name) {
        this.name = name;
        return (T) this;
    }

    @Override
    public T plugin(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        return (T) this;
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
    public T handle(@NotNull ExecutedTask<T> action) {
        this.handle = action;
        return (T) this;
    }

    @Override
    public ExecutedTask<T> getHandle() {
        return handle;
    }

    @Override
    public T taskType(@NotNull TaskType taskType) {
        this.taskType = taskType;
        return (T) this;
    }

    @NotNull
    @Override
    public TaskType getTaskType() {
        return taskType;
    }
}
