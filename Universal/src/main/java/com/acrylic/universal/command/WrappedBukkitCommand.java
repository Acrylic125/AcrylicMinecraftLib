package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class WrappedBukkitCommand<E extends CommandExecuted>
        extends BukkitCommand {

    public static <E extends CommandExecuted> WrappedBukkitCommand<E> wrap(@NotNull Command<E> command) {
        return new WrappedBukkitCommand<>(command);
    }

    private final Command<E> command;

    public WrappedBukkitCommand(Command<E> command) {
        this(command.getName(), command.getDescription(), command.getUsage(), Arrays.asList(command.getAliases()), command);
    }

    public WrappedBukkitCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases, Command<E> command) {
        super(name, description, usageMessage, aliases);
        this.command = command;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        command.executeCommand(command.generateNewCommandExecuted(sender, commandLabel, args, null, command));
        return true;
    }
}
