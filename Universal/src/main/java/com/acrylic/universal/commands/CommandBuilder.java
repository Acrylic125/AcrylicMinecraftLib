package com.acrylic.universal.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class CommandBuilder extends AbstractSimpleCommand {

    private Predicate<CommandExecutedImpl> filter;
    private CommandHandler<CommandExecutedImpl> handler;

    public static CommandBuilder create(@NotNull String name) {
        return new CommandBuilder(name);
    }

    public CommandBuilder(@NotNull String name) {
        super(name);
    }

    public CommandBuilder filter(@Nullable Predicate<CommandExecutedImpl> filter) {
        this.filter = filter;
        return this;
    }

    public CommandBuilder handle(@Nullable CommandHandler<CommandExecutedImpl> handle) {
        this.handler = handle;
        return this;
    }

    public CommandBuilder description(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    public CommandBuilder usage(@NotNull String usage) {
        super.setUsage(usage);
        return this;
    }

    public CommandBuilder aliases(@NotNull String... aliases) {
        super.setAliases(aliases);
        return this;
    }

    public CommandBuilder permissions(@NotNull String... permissions) {
        super.setPermissions(permissions);
        return this;
    }

    public CommandBuilder timer(boolean timer) {
        super.setTimerActive(timer);
        return this;
    }

    @Override
    public void runCommand(CommandExecutedImpl commandExecuted) {
        if (handler != null)
            handler.run(commandExecuted);
    }

    @Override
    public boolean passFilter(CommandExecutedImpl commandExecuted) {
        return filter == null || filter.test(commandExecuted);
    }
}
