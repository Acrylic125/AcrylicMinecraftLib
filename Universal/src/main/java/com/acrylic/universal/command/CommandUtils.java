package com.acrylic.universal.command;

import com.acrylic.universal.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Map;

public class CommandUtils {

    public static boolean validateArgumentUse(@NotNull CommandExecuted commandExecuted, int argIndex) {
        return commandExecuted.getArgs().length - 1 >= argIndex;
    }

    public static byte getByte(@NotNull CommandExecuted commandExecuted, int argIndex, byte defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getByte(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static short getShort(@NotNull CommandExecuted commandExecuted, int argIndex, short defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getShort(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static int getInteger(@NotNull CommandExecuted commandExecuted, int argIndex, int defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getInteger(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static long getLong(@NotNull CommandExecuted commandExecuted, int argIndex, long defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getLong(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static float getFloat(@NotNull CommandExecuted commandExecuted, int argIndex, float defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getFloat(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static double getDouble(@NotNull CommandExecuted commandExecuted, int argIndex, double defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getDouble(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

}
