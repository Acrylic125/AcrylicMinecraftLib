package com.acrylic.universal.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CommandBuilder
        extends BukkitCommand
        implements AbstractCommandBuilder {

    private CommandHandler<AbstractCommandExecuted> commandHandler;
    private CommandHandler<AbstractCommandExecuted> failedConditionsCommandHandler;
    private CommandHandler<AbstractCommandExecuted> noPermissionCommandHandler;
    private AbstractCommandBuilder[] arguments;
    private Predicate<AbstractCommandExecuted> filter;
    private String[] permissions;

    private long time = 0;
    private boolean shouldClock = false;

    public static CommandBuilder create(String command) {
        return new CommandBuilder(command);
    }

    protected CommandBuilder(String command) {
        super(command.toLowerCase());
    }

    @NotNull
    public CommandBuilder setDescription(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    public CommandBuilder setAliases(String... aliases) {
        return setAliases(Arrays.asList(aliases));
    }

    @NotNull
    public CommandBuilder setAliases(@NotNull List<String> aliases) {
        List<String> newAliases = new ArrayList<>();
        for (String alias : aliases) {
            newAliases.add(alias.toLowerCase());
        }
        super.setAliases(newAliases);
        return this;
    }

    @Override
    public void register(@NotNull JavaPlugin plugin) {
        CommandUtils.register(plugin, this);
    }

    @Override
    public CommandHandler<AbstractCommandExecuted> getCommandHandler() {
        return commandHandler;
    }

    @Override
    public CommandHandler<AbstractCommandExecuted> getHandleFailedCondition() {
        return failedConditionsCommandHandler;
    }

    @Override
    public CommandHandler<AbstractCommandExecuted> getHandleNoPermission() {
        return noPermissionCommandHandler;
    }

    @Override
    public Predicate<AbstractCommandExecuted> getFilter() {
        return filter;
    }

    @Override
    public String[] getPermissions() {
        return permissions;
    }

    @Override
    public CommandBuilder handle(CommandHandler<AbstractCommandExecuted> commandHandler) {
        this.commandHandler = commandHandler;
        return this;
    }

    @Override
    public CommandBuilder handleFilter(CommandHandler<AbstractCommandExecuted> commandHandler) {
        this.failedConditionsCommandHandler = commandHandler;
        return this;
    }

    @Override
    public CommandBuilder handleNoPermission(CommandHandler<AbstractCommandExecuted> commandHandler) {
        this.noPermissionCommandHandler = commandHandler;
        return this;
    }

    @Override
    public CommandBuilder filter(Predicate<AbstractCommandExecuted> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public CommandBuilder permissions(String... permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public CommandBuilder aliases(String... aliases) {
        super.setAliases(Arrays.asList(aliases));
        return this;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        execute(new CommandExecuted(sender, args, commandLabel, null));
        return true;
    }

    @Override
    public CommandBuilderExecutor[] getArguments() {
        return arguments;
    }

    @Override
    public CommandBuilder arguments(AbstractCommandBuilder[] arguments) {
        this.arguments = arguments;
        return this;
    }

    @Override
    public boolean isTimerActive() {
        return shouldClock;
    }

    public CommandBuilder timer(boolean isTimerActive) {
        this.shouldClock = isTimerActive;
        return this;
    }

    @Override
    public void setTimerActive(boolean isTimerActive) {
        this.shouldClock = isTimerActive;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

}
