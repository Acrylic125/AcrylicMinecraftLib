package com.acrylic.universal.command;

@FunctionalInterface
public interface CommandHandler<T extends AbstractCommandExecuted> {

    void run(T commandExecutor);

}
