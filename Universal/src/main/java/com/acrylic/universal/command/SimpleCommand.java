package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SimpleCommand
        extends Command<CommandExecutedImpl> {

    @Override
    default CommandExecutedImpl generateNewCommandExecuted(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args,
                                                           @Nullable Command<? extends CommandExecuted> parent,
                                                           @Nullable Command<? extends CommandExecuted> firstParentCommand) {
        return new CommandExecutedImpl(sender, args, commandLabel, this, parent, firstParentCommand);
    }
}
