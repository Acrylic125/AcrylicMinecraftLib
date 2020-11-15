package com.acrylic.universal.threads;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public interface AbstractRunnableBuilder extends Runnable {

    @Override
    default void run() {

    }
}
