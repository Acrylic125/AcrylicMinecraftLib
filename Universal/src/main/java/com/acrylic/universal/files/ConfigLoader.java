package com.acrylic.universal.files;

import com.acrylic.universal.files.configloader.ConfigValue;
import com.acrylic.universal.files.configloader.Configurable;
import com.acrylic.universal.files.bukkit.Configuration;
import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.reflection.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ConfigLoader {

    private static ConfigLoader configLoader = new ConfigLoader();

    public void load(@NotNull FileEditor fileEditor, @NotNull Class<?> loadTo, @Nullable Object obj) {
        FileEditor searcher;
        int i;
        String[] path;
        try {
            for (Field declaredField : loadTo.getDeclaredFields()) {
                ConfigValue configValue = declaredField.getAnnotation(ConfigValue.class);
                if (configValue != null) {
                    searcher = fileEditor;
                    i = 0;
                    path = configValue.path();
                    for (String root : configValue.path()) {
                        if (i >= path.length - 1) {
                            Object o = searcher.getObject(root);
                            Bukkit.broadcastMessage(o + " " + declaredField.getName() + " " + root);
                            if (obj != null || Modifier.isStatic(declaredField.getModifiers()))
                                ReflectionUtils.setField(declaredField, obj, o);
                        } else
                            searcher = fileEditor.getFileEditor(root);
                        i++;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Something went wrong while loading from config loader.");
        }
    }

    public void loadStatics(@NotNull FileEditor fileEditor, @NotNull Class<?> loadTo) {
        load(fileEditor, loadTo, null);
    }

    public void load(@NotNull FileEditor fileEditor, @NotNull Class<?> loadTo, @Nullable Object obj, boolean parentClass) {
        if (parentClass)
            ReflectionUtils.iterateSuperClasses(loadTo, aClass -> load(fileEditor, aClass, obj));
        else
            load(fileEditor, loadTo, obj);
    }

    public void loadStatics(@NotNull FileEditor fileEditor, @NotNull Class<?> loadTo, boolean parentClass) {
        load(fileEditor, loadTo, null, parentClass);
    }

    public void load(@NotNull AbstractFile abstractFile, @NotNull Class<?> loadTo, @Nullable Object obj, boolean parentClass) {
        load(abstractFile.getFileEditor(), loadTo, obj, parentClass);
    }

    public void loadStatics(@NotNull AbstractFile abstractFile, @NotNull Class<?> loadTo, boolean parentClass) {
        load(abstractFile, loadTo, null, parentClass);
    }

    public void loadFromConfigurableClass(@NotNull Class<?> configurableClass, @Nullable Object obj) {
        Configurable configurable = configurableClass.getAnnotation(Configurable.class);
        if (configurable == null)
            throw new IllegalArgumentException("The class, " + configurableClass.getName() + " does not have the @Configurable annotation.");
        Plugin plugin = Bukkit.getPluginManager().getPlugin(configurable.plugin());
        Configuration configuration = ((plugin instanceof JavaPlugin) ?
                new Configuration(configurable.filePath(), (JavaPlugin) plugin) :
                new Configuration(configurable.filePath()));
        configuration.load();
        String resourcePluginStr = configurable.resourcePlugin(), resourcePath = configurable.resourcePath();
        if (!resourcePluginStr.equals("") || !resourcePath.equals("")) {
            Plugin resourcePlugin = Bukkit.getPluginManager().getPlugin(configurable.resourcePlugin());
            if (resourcePlugin instanceof JavaPlugin) {

            } else {
                throw new IllegalArgumentException("If a ");
            }
        }
        FileEditor fileEditor = configuration.getFileEditor();
        for (String path : configurable.root())
            fileEditor = fileEditor.getFileEditor(path);
        load(fileEditor, configurableClass, obj, configurable.loadWithParentClass());
    }

    @NotNull
    public static ConfigLoader getConfigLoader() {
        return configLoader;
    }

    public static void setConfigLoader(@NotNull ConfigLoader configLoader) {
        ConfigLoader.configLoader = configLoader;
    }
}
