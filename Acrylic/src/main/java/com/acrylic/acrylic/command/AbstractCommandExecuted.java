package com.acrylic.acrylic.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface AbstractCommandExecuted {

    CommandSender getSender();

    String[] getArgs();

    default String getArg(int argument) {
        String[] args = getArgs();
        return (args.length > argument) ? args[argument] : null;
    }

    default boolean isPlayer() {
        return getSender() instanceof Player;
    }

}
