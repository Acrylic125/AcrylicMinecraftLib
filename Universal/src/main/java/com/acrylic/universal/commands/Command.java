package com.acrylic.universal.commands;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandHandler;
import com.acrylic.universal.interfaces.Clocker;
import com.acrylic.universal.text.ChatUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Command<E extends CommandExecuted> extends Clocker {

    /**
     * This will run if the CommandSender is allowed to
     * run this command.
     *
     * @param commandExecuted The command being executed.
     */
    void runCommand(E commandExecuted);

    /**
     * This will run if the command used by the CommandSender
     * is disallowed either by {@link #getPermissions()} or
     * {@link #getFilter()}.
     *
     * @param commandExecuted The command that is being executed.
     */
    default void runUnableToUseCommand(E commandExecuted) {}

    /**
     *
     * @return The filter.
     */
    @Nullable
    Predicate<E> getFilter();

    /**
     * The arguments that is extended from this command.
     *
     * For instance, if #getName -> "testcommand" and #getArguments -> {
     *     "argument1", "argument2"
     * }
     * then you may use /testcommand argument1 or /testcommand argument2.
     *
     * @return The arguments.
     */
    @Nullable
    Command<E>[] getArguments();

    /**
     * The permissions required to use this command.
     *
     * @return The permissions.
     */
    @Nullable
    String[] getPermissions();

    /**
     * ENSURE ALL THE ALIASES ARE IN LOWER lowercase.
     *
     * This support arguments of a command.
     *
     * @return The aliases/the alternative names to identify
     * the command executed is this command.
     */
    @NotNull
    String[] getAliases();

    /**
     * The main name of the command.
     *
     * @return The name.
     */
    @NotNull
    String getName();

    @NotNull
    String getUsage();

    default void iterateArguments(@NotNull Consumer<Command<? extends E>> action) {
        Command<E>[] args = getArguments();
        if (args != null && args.length > 0)
            for (Command<? extends E> arg : args)
                action.accept(arg);
    }

    default Command<E> getArgument(E commandExecuted) {
        Command<E>[] args = getArguments();
        if (args == null || args.length <= 0)
            return null;
        String arg = commandExecuted.getArg(0);
        if (arg == null)
            return null;
        arg = arg.toLowerCase();
        for (Command<E> argument : args) {
            if (arg.equals(argument.getName()))
                return argument;
            for (String alias : argument.getAliases())
                if (arg.equals(alias))
                    return argument;
        }
        return null;
    }

    default boolean seekArgument(E commandExecuted) {
        Command<E> argument = getArgument(commandExecuted);
        if (argument == null)
            return false;
        int l = commandExecuted.getArgs().length - 1;
        String[] newArgs = new String[l];
        String[] oldArgs = commandExecuted.getArgs();
        if (l >= 0)
            System.arraycopy(oldArgs, 1, newArgs, 0, l);
        argument.executeCommand(generateNewCommandExecuted(commandExecuted.getSender(), commandExecuted.getLabel(), newArgs, commandExecuted.getFirstParentCommand()));
        return true;
    }

    E generateNewCommandExecuted(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args, @Nullable Command<? extends CommandExecuted> firstParentCommand);

    default boolean hasPermission(E commandExecuted) {
        final String[] permissions = getPermissions();
        final CommandSender sender = commandExecuted.getSender();

        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (sender.hasPermission(permission))
                    return true;
            }
            return false;
        }
        return true;
    }

    default boolean testFilter(E commandExecuted) {
        Predicate<E> filter = getFilter();
        return (filter == null || filter.test(commandExecuted));
    }

    default boolean canUseThisCommand(E commandExecuted) {
        return hasPermission(commandExecuted) && testFilter(commandExecuted);
    }

    default void executeCommand(@NotNull E commandExecuted) {
        if (canUseThisCommand(commandExecuted) &&
                !seekArgument(commandExecuted)) {
            boolean isTimerActive = isTimerActive();
            if (isTimerActive)
                clockTime();
            runCommand(commandExecuted);
            if (isTimerActive)
                System.out.println(ChatUtils.get("The command took " + getTimeDifferenceAsConsoleString()));
        }
    }

}
