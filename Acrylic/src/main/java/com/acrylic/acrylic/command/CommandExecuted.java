package com.acrylic.acrylic.command;

import org.bukkit.command.CommandSender;

public class CommandExecuted implements AbstractCommandExecuted {

    private final String[] args;
    private final CommandSender sender;

    public CommandExecuted(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    @Override
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public String[] getArgs() {
        return args;
    }


}
