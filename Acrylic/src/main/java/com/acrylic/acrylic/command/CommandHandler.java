package com.acrylic.acrylic.command;

@FunctionalInterface
public interface CommandHandler<T extends AbstractCommandExecuted> {

    void run(T commandExecutor);

}
