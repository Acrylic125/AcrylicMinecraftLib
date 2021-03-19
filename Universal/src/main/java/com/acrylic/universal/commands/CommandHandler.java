package com.acrylic.universal.commands;

@FunctionalInterface
public interface CommandHandler<T extends CommandExecutedImpl> {

    void run(T commandExecuted);

}
