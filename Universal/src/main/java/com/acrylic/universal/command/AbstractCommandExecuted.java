package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface AbstractCommandExecuted {

    CommandSender getSender();

    String[] getArgs();

    CommandBuilderExecutor getParentCommandBuilder();

    default String getArg(int argument) {
        String[] args = getArgs();
        return (args.length > argument) ? args[argument] : null;
    }

    default String getArgs(int from) {
        String[] args = getArgs();
        int s = args.length;
        if (s > from) {
            StringBuilder builder = new StringBuilder();
            for (int i = from; i < s; i++) {
                if (i != from)
                    builder.append(" ");
                builder.append(args[i]);
            }
            return builder.toString();
        }
        return null;
    }

    default boolean isPlayer() {
        return getSender() instanceof Player;
    }

}
