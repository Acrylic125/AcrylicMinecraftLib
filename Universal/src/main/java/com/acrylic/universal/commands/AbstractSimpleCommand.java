package com.acrylic.universal.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.acrylic.universal.commands.Command.toComparableCommandString;

public abstract class AbstractSimpleCommand implements SimpleCommand {

    private final String name;
    private Command<CommandExecutedImpl>[] arguments;
    private String[] permissions, aliases;
    private String
            description = "No description.",
            usage;
    private boolean isTimerActive = false;

    public AbstractSimpleCommand(@NotNull String name) {
        this.name = toComparableCommandString(name);
        this.usage = "/" + name;
    }

    @SafeVarargs
    public final void setArguments(@Nullable Command<CommandExecutedImpl>... arguments) {
        this.arguments = arguments;
    }

    @Nullable
    @Override
    public Command<CommandExecutedImpl>[] getArguments() {
        return arguments;
    }

    public void setPermissions(@Nullable String... permissions) {
        this.permissions = permissions;
    }

    @Nullable
    @Override
    public String[] getPermissions() {
        return permissions;
    }

    public void setAliases(@Nullable String... aliases) {
        if (aliases != null) {
            for (int i = 0; i < aliases.length; i++)
                aliases[i] = toComparableCommandString(aliases[i]);
        }
        this.aliases = aliases;
    }

    @NotNull
    @Override
    public String[] getAliases() {
        return aliases;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Override
    public String getDescription() {
        return description;
    }

    public void setUsage(@NotNull String usage) {
        this.usage = usage;
    }

    @NotNull
    @Override
    public String getUsage() {
        return usage;
    }

    public void setTimerActive(boolean isTimerActive) {
        this.isTimerActive = isTimerActive;
    }

    @Override
    public boolean isTimerActive() {
        return isTimerActive;
    }

}
