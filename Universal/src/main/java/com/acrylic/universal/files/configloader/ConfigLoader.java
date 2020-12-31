package com.acrylic.universal.files.configloader;

import com.acrylic.universal.files.AbstractFile;
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
import java.util.function.BiConsumer;

public class ConfigLoader<T> {

    private final AbstractFile file;
    private final FileEditor fileEditor;
    private final Class<T> loadToClass;
    private boolean loadWithParent = false;

    public static <T> ConfigLoader<T> getObjectLoader(@NotNull Class<T> configurableClass) {
        return new ConfigLoader<>(configurableClass);
    }

    public static <T> ConfigLoader<T> getStaticLoader(@NotNull Class<T> configurableClass) {
        return new ConfigLoader<>(configurableClass);
    }

    public ConfigLoader(@NotNull Class<T> configurableClass) {
        Configurable configurable = configurableClass.getAnnotation(Configurable.class);
        if (configurable == null)
            throw new IllegalArgumentException("The class, " + configurableClass.getName() + " does not have the @Configurable annotation.");
        Plugin plugin = Bukkit.getPluginManager().getPlugin(configurable.plugin());
        Configuration configuration = ((plugin instanceof JavaPlugin) ?
                new Configuration(configurable.filePath(), (JavaPlugin) plugin) :
                new Configuration(configurable.filePath()));
        String resourcePluginStr = configurable.resourcePlugin(), resourcePath = configurable.resourcePath();
        if (!resourcePluginStr.equals("")) {
            Plugin resourcePlugin = Bukkit.getPluginManager().getPlugin(configurable.resourcePlugin());
            if (resourcePlugin instanceof JavaPlugin) {
                if (resourcePath.equals(""))
                    configuration.loadFromResources((JavaPlugin) resourcePlugin);
                else
                    configuration.loadFromResources((JavaPlugin) resourcePlugin, resourcePath);
            } else {
                throw new IllegalArgumentException("Undefined resource file from the plugin, " + resourcePlugin + " from the path, " + resourcePath + ". Make sure the resource plugin is a valid plugin.");
            }
        } else {
            configuration.load();
        }
        this.file = configuration;
        this.fileEditor = configuration.getFileEditor().getFileEditor(configurable.root());
        this.loadToClass = configurableClass;
        this.loadWithParent = configurable.loadWithParentClass();
    }

    public ConfigLoader(@NotNull AbstractFile file, @NotNull Class<T> loadToClass) {
        this.file = file;
        this.fileEditor = file.getFileEditor();
        this.loadToClass = loadToClass;
    }

    public boolean isLoadWithParent() {
        return loadWithParent;
    }

    public ConfigLoader<T> setLoadWithParent(boolean loadWithParent) {
        this.loadWithParent = loadWithParent;
        return this;
    }

    @NotNull
    public Class<T> getLoadToClass() {
        return loadToClass;
    }

    @SafeVarargs
    public final void loadObjectThenSave(@NotNull T... objects) {
        for (T object : objects)
            load(object, false);
        save();
    }

    @SafeVarargs
    public final void loadObjectPathPairThenSave(@NotNull ObjectPathPair<T>... objectPathPairs) {
        for (ObjectPathPair<T> objectPair : objectPathPairs)
            load(objectPair.getObj(), false, objectPair.getPath());
        save();
    }

    public void loadObjectThenSave(@NotNull T object) {
        load(object, true);
        save();
    }

    public void staticLoadThenSave() {
        load(null, true);
        save();
    }

    public void load(@Nullable Object obj, boolean staticLoad, @NotNull String... subPath) {
        if (loadWithParent)
            ReflectionUtils.iterateSuperClasses(loadToClass, aClass -> load(aClass, obj, staticLoad, subPath));
        else
            load(this.loadToClass, obj, staticLoad, subPath);
    }

    public void load(@NotNull Class<?> loadToClass, @Nullable Object obj, boolean staticLoad, @NotNull String... subPath) {
        final FileEditor src = fileEditor.getFileEditor(subPath);
        iterateConfigValues(loadToClass, (field, configValue) -> {
            try {
                FileEditor searcher = src;
                int i = 0;
                String[] path = configValue.path();
                for (String root : path) {
                    if (i >= path.length - 1) {
                        if (Modifier.isStatic(field.getModifiers())) {
                            if (staticLoad)
                                setValue(field, configValue, null, searcher, root);
                        } else {
                            if (obj != null)
                                setValue(field, configValue, obj, searcher, root);
                        }
                    } else
                        searcher = fileEditor.getFileEditor(root);
                    i++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Something went wrong while loading from config loader.");
            }
        });

    }

    public void save() {
        file.saveFile();
    }

    private void setValue(@NotNull Field field, @NotNull ConfigValue configValue, @Nullable Object object, @NotNull FileEditor searcher, @NotNull String root) {
        Object o = searcher.getObject(root);
        if (o != null) {
            ReflectionUtils.setField(field, object, o);
        } else if (configValue.isDefaultValue()) {
            Object value = ReflectionUtils.getValueFromField(field, object);
            if (value != null)
                searcher.set(root, value);
        }
    }

    public void iterateConfigValues(@NotNull Class<?> loadToClass, @NotNull BiConsumer<Field, ConfigValue> fieldConfigValueAction) {
        for (Field declaredField : loadToClass.getDeclaredFields()) {
            ConfigValue configValue = declaredField.getAnnotation(ConfigValue.class);
            if (configValue != null)
                fieldConfigValueAction.accept(declaredField, configValue);
        }
    }
}
