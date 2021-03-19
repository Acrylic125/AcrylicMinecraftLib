package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class CommandExecutedImpl implements CommandExecuted {

    private final String[] args;
    private final CommandSender sender;
    private final Command<? extends CommandExecuted> command, parent, firstParent;
    private final String label;

    public CommandExecutedImpl(CommandSender sender, String[] args, String label,
                               Command<? extends CommandExecuted> command,
                               Command<? extends CommandExecuted> parent,
                               Command<? extends CommandExecuted> firstParent) {
        this.sender = sender;
        this.args = args;
        this.label = label;
        this.command = command;
        this.parent = parent;
        this.firstParent = firstParent;
    }

    @NotNull
    public CommandSender getSender() {
        return sender;
    }

    @NotNull
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

    @Nullable
    @Override
    public Command<? extends CommandExecuted> getParent() {
        return parent;
    }

    @NotNull
    public Command<? extends CommandExecuted> getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "CommandExecutedImpl{" +
                "args=" + Arrays.toString(args) +
                ", sender=" + sender +
                ", command=" + command +
                ", parent=" + parent +
                ", firstParent=" + firstParent +
                ", label='" + label + '\'' +
                '}';
    }
}
