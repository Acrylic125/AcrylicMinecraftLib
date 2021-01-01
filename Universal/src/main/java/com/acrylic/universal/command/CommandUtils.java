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

    public static boolean validateArgumentUse(@NotNull AbstractCommandExecuted commandExecuted, int argIndex) {
        return commandExecuted.getArgs().length - 1 >= argIndex;
    }

    public static byte getByte(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, byte defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getByte(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static short getShort(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, short defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getShort(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static int getInteger(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, int defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getInteger(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static long getLong(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, long defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getLong(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static float getFloat(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, float defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getFloat(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    public static double getDouble(@NotNull AbstractCommandExecuted commandExecuted, int argIndex, double defaultValue) {
        return (validateArgumentUse(commandExecuted, argIndex)) ? MathUtils.getDouble(commandExecuted.getArg(argIndex), defaultValue) : defaultValue;
    }

    private static Object getPrivateField(Object object, String field) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static void register(JavaPlugin plugin, Command command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            //Unregister
            Command checkCommand = commandMap.getCommand(command.getName());
            if (checkCommand != null) {
                Map<String, Command> knownCommands = (Map<String, Command>) getPrivateField(commandMap, "knownCommands");
                knownCommands.remove(command.getName());
                for (String alias : command.getAliases()){
                    if (knownCommands.containsKey(alias) && knownCommands.get(alias).toString().contains(command.getName())){
                        knownCommands.remove(alias);
                    }
                }
            }
            //Register
            commandMap.register(plugin.getName(), command);
            bukkitCommandMap.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

}
