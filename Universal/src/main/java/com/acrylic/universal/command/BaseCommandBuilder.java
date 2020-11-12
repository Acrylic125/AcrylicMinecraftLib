package com.acrylic.universal.command;

import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.timer.Timer;

import java.util.List;
import java.util.function.Predicate;

public interface BaseCommandBuilder extends Timer {

    CommandHandler<AbstractCommandExecuted> getCommandHandler();

    CommandHandler<AbstractCommandExecuted> getHandleFailedCondition();

    CommandHandler<AbstractCommandExecuted> getHandleNoPermission();

    Predicate<AbstractCommandExecuted> getFilter();

    String[] getPermissions();

    AbstractCommandBuilder handle(CommandHandler<AbstractCommandExecuted> commandHandler);

    AbstractCommandBuilder handleFilter(CommandHandler<AbstractCommandExecuted> commandHandler);

    AbstractCommandBuilder handleNoPermission(CommandHandler<AbstractCommandExecuted> commandHandler);

    AbstractCommandBuilder filter(Predicate<AbstractCommandExecuted> filter);

    AbstractCommandBuilder permissions(String... permissions);

    AbstractCommandBuilder aliases(String... aliases);

    String getName();

    List<String> getAliases();

    default AbstractCommandBuilder filter(Predicate<AbstractCommandExecuted> filter, CommandHandler<AbstractCommandExecuted> filterHandler) {
        return filter(filter).handleFilter(filterHandler);
    }

    default AbstractCommandBuilder permissions(String[] permissions, CommandHandler<AbstractCommandExecuted> noPermissionCommandHandler) {
        return permissions(permissions)
                .handleNoPermission(noPermissionCommandHandler);
    }

    default AbstractCommandBuilder permissions(String[] permissions, String noPermissionString) {
        return permissions(permissions,
                commandExecutor -> commandExecutor.getSender().sendMessage(ChatUtils.get(noPermissionString)));
    }

}
