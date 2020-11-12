package com.acrylic.universal.command;

import com.acrylic.universal.text.ChatUtils;

import java.util.function.Consumer;

public interface CommandBuilderExecutor extends CommandBuilderChecks {

    CommandBuilderExecutor[] getArguments();

    AbstractCommandBuilder arguments(AbstractCommandBuilder[] arguments);

    default void iterateArguments(Consumer<CommandBuilderExecutor> action) {
        CommandBuilderExecutor[] args = getArguments();
        if (args != null && args.length > 0)
            for (CommandBuilderExecutor arg : args)
                action.accept(arg);
    }

    default CommandBuilderExecutor getArgument(AbstractCommandExecuted abstractCommandExecuted) {
        CommandBuilderExecutor[] args = getArguments();
        if (args == null || args.length <= 0)
            return null;
        String arg = abstractCommandExecuted.getArg(0);
        if (arg == null)
            return null;
        arg = arg.toLowerCase();
        for (CommandBuilderExecutor argument : args) {
            if (arg.equals(argument.getName())) {
                return argument;
            }
            for (String alias : argument.getAliases()) {
                if (arg.equals(alias)) {
                    return argument;
                }
            }
        }
        return null;
    }

    default boolean checkArgument(AbstractCommandExecuted abstractCommandExecuted) {
        CommandBuilderExecutor argument = getArgument(abstractCommandExecuted);
        if (argument == null)
            return false;
        int l = abstractCommandExecuted.getArgs().length - 1;
        String[] newArgs = new String[l];
        String[] oldArgs = abstractCommandExecuted.getArgs();
        if (l >= 0)
            System.arraycopy(oldArgs, 1, newArgs, 0, l);
        argument.execute(new CommandExecuted(abstractCommandExecuted.getSender(), newArgs, this));
        return true;
    }

    default void execute(AbstractCommandExecuted abstractCommandExecuted) {
        CommandHandler<AbstractCommandExecuted> commandHandler = getCommandHandler();
        if (canUseThis(abstractCommandExecuted) &&
                commandHandler != null &&
                !checkArgument(abstractCommandExecuted)) {
            boolean isTimerActive = isTimerActive();
            if (isTimerActive)
                clockTime();
            commandHandler.run(abstractCommandExecuted);
            if (isTimerActive)
                System.out.println(ChatUtils.get("The command took " + getTimeFromLastClockedAsString()));
        }
    }

}
