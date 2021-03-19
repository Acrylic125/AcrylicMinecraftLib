package com.acrylic.universal.command;

import com.acrylic.universal.interfaces.PluginRegister;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.utils.TimeConverter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;

public interface Command<E extends CommandExecuted>
        extends PluginRegister {

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
     * {@link #passFilter(CommandExecuted)})}.
     *
     * @param commandExecuted The command that is being executed.
     */
    default void runUnableToUseCommand(E commandExecuted) {}

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
    @Nullable
    String[] getAliases();

    /**
     * The main name of the command.
     *
     * @return The name.
     */
    @NotNull
    String getName();

    @NotNull
    String getDescription();

    @NotNull
    String getUsage();

    /**
     * Iterates through all the arguments of this command.
     *
     * @param action The action.
     */
    default void iterateArguments(@NotNull Consumer<Command<E>> action) {
        Command<E>[] args = getArguments();
        if (args != null && args.length > 0)
            for (Command<E> arg : args)
                action.accept(arg);
    }

    /**
     * Iterates through all the arguments of this command and
     * it's children.
     *
     * @param action The action.
     */
    default void iterateArgumentsWithChild(@NotNull Consumer<Command<E>> action) {
        Command<E>[] args = getArguments();
        if (args != null && args.length > 0)
            for (Command<E> arg : args) {
                action.accept(arg);
                arg.iterateArgumentsWithChild(action);
            }
    }

    default Command<E> getArgument(E commandExecuted) {
        Command<E>[] args = getArguments();
        if (args == null || args.length <= 0)
            return null;
        String arg = commandExecuted.getArg(0);
        if (arg == null)
            return null;
        arg = toComparableCommandString(arg);
        for (Command<E> argument : args) {
            if (arg.equals(argument.getName()))
                return argument;
            String[] aliases = argument.getAliases();
            if (aliases != null) {
                for (String alias : aliases)
                    if (arg.equals(alias))
                        return argument;
            }
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
        argument.executeCommand(
                generateNewCommandExecuted(commandExecuted.getSender(), commandExecuted.getLabel(), newArgs,
                        this,
                        commandExecuted.getFirstParentCommand())
        );
        return true;
    }

    E generateNewCommandExecuted(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args,
                                 @Nullable Command<? extends CommandExecuted> parent,
                                 @Nullable Command<? extends CommandExecuted> firstParentCommand);

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

    default boolean passFilter(E commandExecuted) {
        return true;
    }

    default boolean canUseThisCommand(E commandExecuted) {
        return hasPermission(commandExecuted) && passFilter(commandExecuted);
    }

    default void executeCommand(@NotNull E commandExecuted) {
        if (canUseThisCommand(commandExecuted) &&
                !seekArgument(commandExecuted)) {
            long time = System.currentTimeMillis();
            runCommand(commandExecuted);
            if (isTimerActive())
                System.out.println(ChatUtils.get("The command took " + TimeConverter.CONSOLE.convert(System.currentTimeMillis() - time)));
        }
    }

    static String toComparableCommandString(String string) {
        return string.toLowerCase(Locale.ROOT);
    }

    @Override
    default void register(@NotNull JavaPlugin javaPlugin) {
        CommandRegistry.register(javaPlugin, WrappedBukkitCommand.wrap(this));
    }

    boolean isTimerActive();

}
