package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;

import java.util.function.Predicate;

public interface CommandBuilderChecks extends BaseCommandBuilder {

    default boolean hasPermission(AbstractCommandExecuted abstractCommandExecuted) {
        final String[] permissions = getPermissions();
        final CommandSender sender = abstractCommandExecuted.getSender();

        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (sender.hasPermission(permission))
                    return true;
            }
            CommandHandler<AbstractCommandExecuted> noPermAction = getHandleNoPermission();
            if (noPermAction != null) {
                noPermAction.run(abstractCommandExecuted);
            }
            return false;
        }
        return true;
    }

    default boolean canUseThis(AbstractCommandExecuted abstractCommandExecuted) {
        if (!hasPermission(abstractCommandExecuted))
            return false;
        Predicate<AbstractCommandExecuted> filter = getFilter();
        if ((filter != null && !filter.test(abstractCommandExecuted))) {
            CommandHandler<AbstractCommandExecuted> failedAction = getHandleFailedCondition();
            if (failedAction != null) {
                failedAction.run(abstractCommandExecuted);
            }
            return false;
        }
        return true;
    }

}
