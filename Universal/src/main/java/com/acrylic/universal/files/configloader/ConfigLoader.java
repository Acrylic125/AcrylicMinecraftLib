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

public class ConfigLoader {

    private final AbstractFile file;
    private final FileEditor fileEditor;
    private final Object object;
    private final Class<?> loadToClass;
    private boolean staticLoad = false;
    private boolean loadWithParent = false;

    public static <T> ConfigLoader getObjectLoader(@NotNull Class<T> configurableClass, @NotNull T o) {
        return new ConfigLoader(configurableClass, o);
    }

    public static ConfigLoader getStaticLoader(@NotNull Class<?> configurableClass) {
        return new ConfigLoader(configurableClass, null);
    }

    public ConfigLoader(@NotNull Class<?> configurableClass, @Nullable Object object) {
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
        this.object = object;
        this.loadToClass = configurableClass;
        if (object == null)
            staticLoad = true;
        this.loadWithParent = configurable.loadWithParentClass();
    }

    public ConfigLoader(@NotNull AbstractFile file, @NotNull Object object) {
        this.file = file;
        this.fileEditor = file.getFileEditor();
        this.object = object;
        this.loadToClass = object.getClass();
    }

    public boolean isStaticLoad() {
        return staticLoad;
    }

    public ConfigLoader setStaticLoad(boolean staticLoad) {
        this.staticLoad = staticLoad;
        return this;
    }

    public boolean isObjectLoad() {
        return object != null;
    }

    public boolean isLoadWithParent() {
        return loadWithParent;
    }

    public ConfigLoader setLoadWithParent(boolean loadWithParent) {
        this.loadWithParent = loadWithParent;
        return this;
    }

    public void loadThenSave() {
        load();
        save();
    }

    public void save() {
        file.saveFile();
    }

    public void load() {
        load(loadWithParent);
    }

    public void load(boolean withParent) {
        if (withParent)
            ReflectionUtils.iterateSuperClasses(loadToClass, this::load);
        else
            load(this.loadToClass);
    }

    public void load(@NotNull Class<?> loadToClass) {
        iterateConfigValues(loadToClass, (field, configValue) -> {
            try {
                FileEditor searcher = fileEditor;
                int i = 0;
                String[] path = configValue.path();
                for (String root : configValue.path()) {
                    if (i >= path.length - 1) {
                        if (isStaticLoad() && Modifier.isStatic(field.getModifiers()))
                            setValue(field, configValue, null, searcher, root);
                        else if (isObjectLoad())
                            setValue(field, configValue, object, searcher, root);
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
