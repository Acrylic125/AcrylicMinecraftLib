package com.acrylic.universal.command;

@FunctionalInterface
public interface CommandHandler<T extends CommandExecutedImpl> {

    void run(T commandExecuted);

}
