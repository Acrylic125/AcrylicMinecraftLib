package com.acrylic.universal.files.configloader;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation represents a variable that can be declared
 * from a configuration file.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ConfigValue {

    /**
     * The path of this config value.
     *
     * If a value is nested within another value (i.e. within a map),
     * define the path as {"a", "b"} to retrieve a value from the
     * config value path,
     *
     * a:
     *  b: value
     *
     * @return The path.
     */
    @NotNull
    String[] path();

    /**
     *
     * Whether this variable is the default value.
     *
     * If it is and if the config does not have this variable specified,
     * this variable be declared as the default.
     *
     * @return If this variable is the default value.
     */
    boolean isDefaultValue() default true;

}
