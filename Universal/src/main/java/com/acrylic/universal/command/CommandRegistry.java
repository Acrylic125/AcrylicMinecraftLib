package com.acrylic.universal.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Map;

public final class CommandRegistry {

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
    public static void register(JavaPlugin plugin, org.bukkit.command.Command command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            //Unregister
            org.bukkit.command.Command checkCommand = commandMap.getCommand(command.getName());
            if (checkCommand != null) {
                Map<String, org.bukkit.command.Command> knownCommands = (Map<String, Command>) getPrivateField(commandMap, "knownCommands");
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
