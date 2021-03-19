package com.acrylic.universal.commands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandExecutedImpl implements CommandExecuted {

    private final String[] args;
    private final CommandSender sender;
    private final Command<? extends CommandExecuted> command, firstParent;
    private final String label;

    public CommandExecutedImpl(CommandSender sender, String[] args, String label,
                               Command<? extends CommandExecuted> command,
                               Command<? extends CommandExecuted> firstParent) {
        this.sender = sender;
        this.args = args;
        this.label = label;
        this.command = command;
        this.firstParent = firstParent;
    }

    @NotNull
    public CommandSender getSender() {
        return sender;
    }

    public String getLabel() {
        return label;
    }

    @NotNull
    public String[] getArgs() {
        return args;
    }

    @Nullable
    @Override
    public Command<?> getFirstParentCommand() {
        return firstParent;
    }

    @NotNull
    public Command<? extends CommandExecuted> getCommand() {
        return command;
    }


}
