package com.acrylic.universal.command;

import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.interfaces.Clocker;

import java.util.List;
import java.util.function.Predicate;

public interface BaseCommandBuilder extends Clocker {

    CommandHandler<AbstractCommandExecuted> getCommandHandler();

    CommandHandler<AbstractCommandExecuted> getHandleFailedCondition();

    String[] getPermissions();

    AbstractCommandBuilder permissions(String... permissions);

    default AbstractCommandBuilder permissions(String[] permissions, CommandHandler<AbstractCommandExecuted> noPermissionCommandHandler) {
        return permissions(permissions)
                .handleNoPermission(noPermissionCommandHandler);
    }

    default AbstractCommandBuilder permissions(String[] permissions, String noPermissionString) {
        return permissions(permissions,
                commandExecutor -> commandExecutor.getSender().sendMessage(ChatUtils.get(noPermissionString)));
    }

    AbstractCommandBuilder handleNoPermission(CommandHandler<AbstractCommandExecuted> commandHandler);

    CommandHandler<AbstractCommandExecuted> getHandleNoPermission();

    Predicate<AbstractCommandExecuted> getFilter();

    AbstractCommandBuilder filter(Predicate<AbstractCommandExecuted> filter);

    default AbstractCommandBuilder filter(Predicate<AbstractCommandExecuted> filter, CommandHandler<AbstractCommandExecuted> filterHandler) {
        return filter(filter).handleFilter(filterHandler);
    }

    AbstractCommandBuilder handleFilter(CommandHandler<AbstractCommandExecuted> commandHandler);

    List<String> getAliases();

    AbstractCommandBuilder aliases(String... aliases);

    String getName();

    AbstractCommandBuilder handle(CommandHandler<AbstractCommandExecuted> commandHandler);

}
