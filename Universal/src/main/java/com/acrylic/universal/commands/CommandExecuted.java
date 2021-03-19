package com.acrylic.universal.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandExecuted {

    @Nullable
    Command<? extends CommandExecuted> getFirstParentCommand();

    @NotNull
    Command<? extends CommandExecuted> getCommand();

    @NotNull
    CommandSender getSender();

    @NotNull
    String[] getArgs();

    @NotNull
    String getLabel();

    default String getArg(int argument) {
        String[] args = getArgs();
        return (args.length > argument) ? args[argument] : null;
    }

    default String getArgsAsString(int from) {
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

    default boolean isExecutedByPlayer() {
        return getSender() instanceof Player;
    }

}
