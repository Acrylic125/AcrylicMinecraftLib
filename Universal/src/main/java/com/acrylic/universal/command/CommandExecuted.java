package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;

public class CommandExecuted implements AbstractCommandExecuted {

    private final String[] args;
    private final CommandSender sender;
    private final CommandBuilderExecutor commandBuilder;
    private final String label;

    public CommandExecuted(CommandSender sender, String[] args, String label, CommandBuilderExecutor commandBuilder) {
        this.sender = sender;
        this.args = args;
        this.label = label;
        this.commandBuilder = commandBuilder;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public CommandBuilderExecutor getParentCommandBuilder() {
        return commandBuilder;
    }

}
