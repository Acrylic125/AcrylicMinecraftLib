package com.acrylic.universal.command;

import lombok.experimental.UtilityClass;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Map;

@UtilityClass
public class CommandUtils {

    private Object getPrivateField(Object object, String field) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }

    @SuppressWarnings("unchecked")
    public void register(JavaPlugin plugin, Command command) {
        try {
            final Field bukkitCommandMap = plugin.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) bukkitCommandMap.get(plugin.getServer());

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
            bukkitCommandMap.setAccessible(false);
            commandMap.register(plugin.getName(), command);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

}
