package com.acrylic.acrylic.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

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

    public CommandBuilder(String command) {
        super(command.toLowerCase());
    }

    public CommandBuilder setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    public CommandBuilder setAliases(String... aliases) {
        return setAliases(Arrays.asList(aliases));
    }

    public CommandBuilder setAliases(List<String> aliases) {
        List<String> newAliases = new ArrayList<>();
        for (String alias : aliases) {
            newAliases.add(alias.toLowerCase());
        }
        super.setAliases(newAliases);
        return this;
    }

    @Override
    public void register(JavaPlugin plugin) {
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
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        execute(new CommandExecuted(sender, args));
        return true;
    }

    @Override
    public CommandBuilderExecutor[] getArguments() {
        return arguments;
    }

    @Override
    public AbstractCommandBuilder arguments(AbstractCommandBuilder[] arguments) {
        this.arguments = arguments;
        return this;
    }

}
