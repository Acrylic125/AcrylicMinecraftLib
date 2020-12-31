package com.acrylic.universal.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public class ReflectionUtils {

    public static void iterateSuperClasses(@NotNull Class<?> clazz, Consumer<Class<?>> actionOfEachClass) {
        while (clazz != null) {
            actionOfEachClass.accept(clazz);
            clazz = clazz.getSuperclass();
        }
    }

    public static void setStaticField(@NotNull Field field, @Nullable Object value) {
        setField(field, null, value);
    }

    public static void setField(@NotNull Field field, @Nullable Object obj, @Nullable Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
    }

    /**
     * Retrieves the Field from class, clazz, of fieldName.
     * @param clazz The class.
     * @param fieldName The field name.
     * @return The field if found in the hierarchy.
     */
    public static Field getField(@NotNull Class<?> clazz, @NotNull final String fieldName) {
        Field found = null;
        while (found == null && clazz != null) {
            try {
                found = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                clazz = clazz.getSuperclass();
            }
        }
        if (found != null)
            found.setAccessible(true);
        return found;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getField(Class<?> clazz, String fieldName, Class<T> returnType) {
        Field field = getField(clazz, fieldName);
        if (field != null && field.getType().isAssignableFrom(returnType)) {
            try {
                return (T) field.get(null);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Nullable
    public static Object getValueFromField(@NotNull Field field, @Nullable Object obj) {
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
        return null;
    }

}
