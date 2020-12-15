package com.acrylic.universal.threads;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ExecutedTask<T extends Scheduler<T>> {

    /**
     * This functional interface gets called when the #run method gets
     * ran in either the Async thread pool or Bukkit.
     *
     * @param scheduler The scheduler that ran this.
     */
    void run(@NotNull T scheduler);

}
