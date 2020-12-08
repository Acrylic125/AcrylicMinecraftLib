package com.acrylic.universal.reflection;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class ReflectionUtils {

    /**
     * Retrieves the Field from class, clazz, of fieldName.
     * @param clazz The class.
     * @param fieldName The field name.
     * @return The field if found in the hierarchy.
     */
    public static Field getField(Class<?> clazz, @NotNull final String fieldName) {
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
    public static  <T> T getField(Class<?> clazz, String fieldName, Class<T> returnType) {
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

}
